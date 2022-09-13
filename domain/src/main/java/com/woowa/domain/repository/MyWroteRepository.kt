package com.woowa.domain.repository

import androidx.paging.PagingData
import com.woowa.domain.model.mywrote.MyWroteContents
import kotlinx.coroutines.flow.Flow

interface MyWroteRepository {

    suspend fun getMyWrote(status: String): Flow<PagingData<MyWroteContents>>
}