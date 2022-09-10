package com.woowa.data.di

import com.woowa.data.remote.repository.AuthenticationRepositoryImpl
import com.woowa.domain.repository.AuthenticationRepository
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
}