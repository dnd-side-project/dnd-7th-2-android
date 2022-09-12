package com.woowa.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.woowa.data.remote.soruce.paging.MyWrotePagingSource
import com.woowa.data.remote.service.MyWroteService
import com.woowa.domain.model.mywrote.MyWroteContents
import com.woowa.domain.repository.MyWroteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class MyWroteRepositoryImpl @Inject constructor(
    private val myWroteService: MyWroteService
) : MyWroteRepository {

    override suspend fun getMyWrote(status: String): Flow<PagingData<MyWroteContents>> {
        return Pager(PagingConfig(pageSize = 10)) {
            MyWrotePagingSource(myWroteService, status)
        }.flow
    }
}