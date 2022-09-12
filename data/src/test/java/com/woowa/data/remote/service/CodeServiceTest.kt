package com.woowa.data.remote.service

import com.google.common.truth.Truth
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.woowa.data.remote.model.response.ErrorResponse
import com.woowa.domain.model.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import mockwebserver3.MockResponse
import mockwebserver3.MockWebServer
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.io.File

@ExperimentalCoroutinesApi
class CodeServiceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var codeService: CodeService

    @BeforeEach
    fun setUp() {
        mockWebServer = MockWebServer()
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        codeService = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(mockWebServer.url(""))
            .build()
            .create()
    }

    @Test
    fun `코드를 조회 할 수 있다`() = runTest {
        // Given
        val responseJson =
            File("src/test/java/resources/member/code_success.json").readText()
        val response = MockResponse()
            .setResponseCode(200)
            .setBody(responseJson)
        mockWebServer.enqueue(response)

        // When
        val actualResponse = codeService.getCode(listOf("ReviewTag"))

        // Then
        val codeEnum = hashMapOf(
            Pair(
                "ReviewTag",
                listOf(
                    CodeEnum("RESPONSIBILITY", "책임감 굿"),
                    CodeEnum("DEAD_LINE", "마감을 칼같이"),
                    CodeEnum("MOOD_MAKER", "분위기 메이커"),
                    CodeEnum("TIME_MANNERS", "시간매너 끝판왕")
                )
            )
        )
        assertAll(
            { Truth.assertThat(actualResponse.success).isTrue() },
            { Truth.assertThat(actualResponse.data?.codeEnum).isEqualTo(codeEnum) },
            { Truth.assertThat(actualResponse.error).isEqualTo(ErrorResponse.of()) }
        )
    }

    @Test
    fun `존재하지 않는 코드를 조회 하였다`() = runTest {
        // Given
        val responseJson =
            File("src/test/java/resources/member/code_fail.json").readText()
        val response = MockResponse()
            .setResponseCode(200)
            .setBody(responseJson)
        mockWebServer.enqueue(response)

        // When
        val actualResponse = codeService.getCode(listOf("NotFoundCode1", "NotFoundCode2"))

        // Then
        val codeEnum = hashMapOf(
            Pair("NotFoundCode1", listOf<CodeEnum>()),
            Pair("NotFoundCode2", listOf<CodeEnum>())
        )
        assertAll(
            { Truth.assertThat(actualResponse.success).isTrue() },
            { Truth.assertThat(actualResponse.data?.codeEnum).isEqualTo(codeEnum) },
            { Truth.assertThat(actualResponse.error).isEqualTo(ErrorResponse.of()) }
        )
    }
}