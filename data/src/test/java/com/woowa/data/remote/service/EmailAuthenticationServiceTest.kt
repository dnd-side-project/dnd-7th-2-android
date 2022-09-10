package com.woowa.data.remote.service

import com.google.common.truth.Truth.assertThat
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.woowa.data.remote.model.response.ErrorResponse
import com.woowa.domain.model.email.Email
import com.woowa.domain.model.email.EmailAuth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.io.File

@ExperimentalCoroutinesApi
internal class EmailAuthenticationServiceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var emailAuthenticationService: EmailAuthenticationService

    @BeforeEach
    fun setUp() {
        mockWebServer = MockWebServer()
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        emailAuthenticationService = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(mockWebServer.url(""))
            .build()
            .create()
    }

    @Test
    fun `이메일 인증을 보낼 수 있다`() = runTest {
        //given
        val responseJson =
            File("src/test/java/resources/email/email_success_send.json").readText()
        val response = MockResponse()
            .setResponseCode(200)
            .setBody(responseJson)
        mockWebServer.enqueue(response)

        // when
        val actualResponse = emailAuthenticationService.sendEmail(Email("skaxodn97@gmail.com", 1L))

        // then
        assertAll(
            { assertThat(actualResponse.success).isTrue() },
            { assertThat(actualResponse.data).isNull() },
            { assertThat(actualResponse.error).isEqualTo(ErrorResponse.of()) }
        )
    }

    @Test
    fun `이메일 인증이 유효한지 판단할 수 있다`() = runTest {
        //given
        val responseJson =
            File("src/test/java/resources/email/email_success_auth_check.json").readText()
        val response = MockResponse()
            .setResponseCode(200)
            .setBody(responseJson)
        mockWebServer.enqueue(response)

        // when
        val actualResponse =
            emailAuthenticationService.authEmail(EmailAuth("test@email.com", "123456"))

        // then
        assertAll(
            { assertThat(actualResponse.success).isTrue() },
            { assertThat(actualResponse.data?.email).isEqualTo("test@email.com") },
            { assertThat(actualResponse.data?.authenticated).isTrue() },
        )
    }
}