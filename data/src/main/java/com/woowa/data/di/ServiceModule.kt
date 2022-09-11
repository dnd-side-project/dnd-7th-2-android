package com.woowa.data.di

import com.woowa.data.remote.service.AuthenticationService
import com.woowa.data.remote.service.CodeService
import com.woowa.data.remote.service.MemberService
import com.woowa.data.remote.service.EmailAuthenticationService
import com.woowa.data.remote.service.UniversityService
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
        return retrofit.create(AuthenticationService::class.java)
    }

    @Provides
    @Singleton
    fun provideCodeService(retrofit: Retrofit): CodeService {
        return retrofit.create(CodeService::class.java)
    }
    
    @Provides
    @Singleton
    fun provideUniversityService(retrofit: Retrofit): UniversityService {
        return retrofit.create(UniversityService::class.java)
    }

    @Provides
    @Singleton
    fun provideMemberService(retrofit: Retrofit): MemberService {
        return retrofit.create(MemberService::class.java)
    }
    
    @Provides
    @Singleton
    fun provideEmailAuthenticationService(retrofit: Retrofit): EmailAuthenticationService {
        return retrofit.create(EmailAuthenticationService::class.java)
    }
}