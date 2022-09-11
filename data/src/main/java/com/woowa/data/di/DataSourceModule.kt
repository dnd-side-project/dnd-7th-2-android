package com.woowa.data.di

import com.woowa.data.remote.soruce.CodeDataSource
import com.woowa.data.remote.soruce.CodeDataSourceImpl
import com.woowa.data.remote.soruce.MemberDataSource
import com.woowa.data.remote.soruce.MemberDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindCodeDataSource(codeDataSourceImpl: CodeDataSourceImpl): CodeDataSource

    @Binds
    @Singleton
    abstract fun bindMemberDataSource(memberDataSourceImpl: MemberDataSourceImpl): MemberDataSource
}