package com.woowa.data.remote.soruce.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.woowa.data.remote.service.MyWroteService
import com.woowa.domain.model.ApiResult
import com.woowa.domain.model.ErrorResult
import com.woowa.domain.model.mywrote.MyWroteContents
import javax.inject.Inject

internal class MyWrotePagingSource @Inject constructor(
    private val myWroteService: MyWroteService,
    private val status: String
) : PagingSource<Int, MyWroteContents>() {

    override fun getRefreshKey(state: PagingState<Int, MyWroteContents>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MyWroteContents> {
        val curPage = params.key ?: 1

        return try {
            val apiResponse = myWroteService.getMyWrote(curPage, PER_SIZE, status)
            val apiResult = ApiResult(
                success = apiResponse.success,
                data = apiResponse.data?.toMyWrote(),
                error = ErrorResult.of(apiResponse.error.message, apiResponse.error.errors)
            )

            if (apiResult.success) {
                val data = apiResult.data ?: throw Exception("Empty data")
                val endOfPaginationReached = !data.next

                if (data.totalPages >= curPage) {
                    LoadResult.Page(
                        data = data.contents,
                        prevKey = if (curPage == 1) null else curPage - 1,
                        nextKey = if (endOfPaginationReached) null else curPage + 1
                    )
                } else {
                    LoadResult.Page(
                        data = emptyList(),
                        prevKey = null,
                        nextKey = null
                    )
                }
            } else {
                throw Exception("Network response failed")
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val PER_SIZE = 10
    }
}