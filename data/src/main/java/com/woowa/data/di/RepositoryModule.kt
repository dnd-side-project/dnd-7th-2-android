package com.woowa.data.di

import com.woowa.data.remote.repository.*
import com.woowa.data.remote.repository.AuthenticationRepositoryImpl
import com.woowa.data.remote.repository.CodeRepositoryImpl
import com.woowa.data.remote.repository.EmailAuthenticationRepositoryImpl
import com.woowa.data.remote.repository.MemberRepositoryImpl
import com.woowa.data.remote.repository.MyWroteRepositoryImpl
import com.woowa.data.remote.repository.UniversityRepositoryImpl
import com.woowa.domain.repository.*
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
    fun provideCode(codeRepositoryImpl: CodeRepositoryImpl): CodeRepository

    @Binds
    @Singleton
    fun provideMember(memberRepositoryImpl: MemberRepositoryImpl): MemberRepository
    
    @Binds
    @Singleton
    fun provideUniversity(universityRepositoryImpl: UniversityRepositoryImpl): UniversityRepository

    @Binds
    @Singleton
    fun provideEmailAuthentication(emailAuthenticationRepositoryImpl: EmailAuthenticationRepositoryImpl): EmailAuthenticationRepository

    @Binds
    @Singleton
    fun provideMyWrote(myWroteRepositoryImpl: MyWroteRepositoryImpl): MyWroteRepository
}