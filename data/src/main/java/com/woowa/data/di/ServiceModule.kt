package com.woowa.data.di

import com.woowa.data.remote.service.AuthenticationService
import com.woowa.data.remote.service.EmailAuthenticationService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal object ServiceModule {

    @Provides
    @Singleton
    fun provideAuthenticationService(retrofit: Retrofit): AuthenticationService {
        return retrofit.create(com.woowa.data.remote.service.AuthenticationService::class.java)
    }

    @Provides
    @Singleton
    fun provideEmailAuthenticationService(retrofit: Retrofit): EmailAuthenticationService {
        return retrofit.create(EmailAuthenticationService::class.java)
    }
}