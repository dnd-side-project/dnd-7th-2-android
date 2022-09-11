package com.woowa.data.di

import com.woowa.data.remote.repository.AuthenticationRepositoryImpl
import com.woowa.data.remote.repository.CodeRepositoryImpl
import com.woowa.data.remote.repository.MemberRepositoryImpl
import com.woowa.domain.repository.AuthenticationRepository
import com.woowa.domain.repository.CodeRepository
import com.woowa.domain.repository.MemberRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal interface RepositoryModule {

    @Binds
    @Singleton
    fun provideAuthentication(authenticationRepositoryImpl: AuthenticationRepositoryImpl): AuthenticationRepository

    @Binds
    @Singleton
    fun provideCodeRepository(codeRepositoryImpl: CodeRepositoryImpl): CodeRepository

    @Binds
    @Singleton
    fun provideMemberRepository(memberRepositoryImpl: MemberRepositoryImpl): MemberRepository
}