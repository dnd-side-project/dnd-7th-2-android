package com.woowa.data.remote.service

import com.google.common.truth.Truth.assertThat
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.woowa.data.remote.model.response.ErrorResponse
import com.woowa.data.remote.model.response.TokenDto
import com.woowa.domain.model.Login
import com.woowa.domain.model.Reissue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.io.File

@ExperimentalCoroutinesApi
internal class AuthenticationServiceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var authenticationService: AuthenticationService

    @BeforeEach
    fun setUp() {
        mockWebServer = MockWebServer()
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        authenticationService = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(mockWebServer.url(""))
            .build()
            .create()
    }

    @Test
    fun `인증되지 않은 사용자는 접근할 수 없다`() = runTest {
        //given
        val responseJson =
            File("src/test/java/resources/auth/auth_fail_unauthorized.json").readText()
        val response = MockResponse()
            .setResponseCode(401)
            .setBody(responseJson)
        mockWebServer.enqueue(response)

        assertThrows<HttpException> {
            // when
            val actualResponse = authenticationService.reissue(Reissue("", ""))

            // then
            assertAll(
                { assertThat(actualResponse.success).isFalse() },
                { assertThat(actualResponse.data).isNull() },
                { assertThat(actualResponse.error.code).isEqualTo("HANDLE_UNAUTHORIZED") },
                { assertThat(actualResponse.error.message).isEqualTo("인증되지 않은 사용자입니다.") },
                { assertThat(actualResponse.error.errors).isEmpty() }
            )
        }
    }

    @Test
    fun `인증 시 유효하지 않은 입력 값을 확인해야한다`() = runTest {
        //given
        val responseJson =
            File("src/test/java/resources/auth/auth_fail_invalid_input_value.json").readText()
        val response = MockResponse()
            .setResponseCode(400)
            .setBody(responseJson)
        mockWebServer.enqueue(response)

        assertThrows<HttpException> {
            // when
            val actualResponse = authenticationService.reissue(Reissue("", ""))

            // then
            assertAll(
                { assertThat(actualResponse.success).isTrue() },
                { assertThat(actualResponse.data).isNull() },
                { assertThat(actualResponse.error.code).isEqualTo("INVALID_INPUT_VALUE") },
                { assertThat(actualResponse.error.message).isEqualTo("유효하지 않은 입력 값입니다.") },
                { assertThat(actualResponse.error.errors.first().field).isEqualTo("input-field") },
                { assertThat(actualResponse.error.errors.first().value).isEqualTo("input-value") },
                { assertThat(actualResponse.error.errors.first().reason).isEqualTo("error-reason") }
            )
        }
    }

    @Test
    fun `로그인을 할 수 있다`() = runTest {
        //given
        val responseJson =
            File("src/test/java/resources/auth/auth_success_login.json").readText()
        val response = MockResponse()
            .setResponseCode(200)
            .setBody(responseJson)
        mockWebServer.enqueue(response)

        // when
        val actualResponse = authenticationService.login(Login("taewoo", "123456"))

        assertAll(
            { assertThat(actualResponse.success).isTrue() },
            {
                assertThat(actualResponse.data).isEqualTo(
                    TokenDto(
                        "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0QGVtYWlsLmNvbSIsInRva2VuX3R5cGUiOiJhY2Nlc3NfdG9rZW4iLCJpYXQiOjE2NjA1MzMxMzUsImV4cCI6MTY2MDUzNjczNX0.Dl7w6abygImRVfPzJJPze__p_Ig275E3x0n_fZsxCaU",
                        "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0QGVtYWlsLmNvbSIsInRva2VuX3R5cGUiOiJyZWZyZXNoX3Rva2VuIiwiaWF0IjoxNjYwNTMzMTM1LCJleHAiOjE2NjMxMjUxMzV9.UnMeqHym_-0DMCVWcFBdh2hGax378L6BmJhMHeo1ny8"
                    )
                )
            },
            { assertThat(actualResponse.error).isEqualTo(ErrorResponse.of()) }
        )
    }

    @Test
    fun `로그아웃을 할 수 있다`() = runTest {
        //given
        val responseJson =
            File("src/test/java/resources/auth/auth_success_logout.json").readText()
        val response = MockResponse()
            .setResponseCode(200)
            .setBody(responseJson)
        mockWebServer.enqueue(response)

        // when
        val actualResponse = authenticationService.logout()

        assertAll(
            { assertThat(actualResponse.success).isTrue() },
            { assertThat(actualResponse.data).isNull() },
            { assertThat(actualResponse.error).isEqualTo(ErrorResponse.of()) }
        )
    }

    @Test
    fun `토큰을 재발급 받을 수 있다()`() = runTest {
        //given
        val responseJson =
            File("src/test/java/resources/auth/auth_success_login.json").readText()
        val response = MockResponse()
            .setResponseCode(200)
            .setBody(responseJson)
        mockWebServer.enqueue(response)

        // when
        val actualResponse = authenticationService.reissue(
            Reissue(
                "",
                "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0QGVtYWlsLmNvbSIsInRva2VuX3R5cGUiOiJyZWZyZXNoX3Rva2VuIiwiaWF0IjoxNjYwNTMzMTM1LCJleHAiOjE2NjMxMjUxMzV9.UnMeqHym_-0DMCVWcFBdh2hGax378L6BmJhMHeo1ny8"
            )
        )

        assertAll(
            { assertThat(actualResponse.success).isTrue() },
            {
                assertThat(actualResponse.data).isEqualTo(
                    TokenDto(
                        "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0QGVtYWlsLmNvbSIsInRva2VuX3R5cGUiOiJhY2Nlc3NfdG9rZW4iLCJpYXQiOjE2NjA1MzMxMzUsImV4cCI6MTY2MDUzNjczNX0.Dl7w6abygImRVfPzJJPze__p_Ig275E3x0n_fZsxCaU",
                        "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0QGVtYWlsLmNvbSIsInRva2VuX3R5cGUiOiJyZWZyZXNoX3Rva2VuIiwiaWF0IjoxNjYwNTMzMTM1LCJleHAiOjE2NjMxMjUxMzV9.UnMeqHym_-0DMCVWcFBdh2hGax378L6BmJhMHeo1ny8"
                    )
                )
            },
            { assertThat(actualResponse.error).isEqualTo(ErrorResponse.of()) }
        )
    }
}