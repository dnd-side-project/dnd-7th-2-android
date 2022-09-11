package com.woowa.data.remote.service

import com.google.common.truth.Truth.assertThat
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.woowa.data.remote.model.response.ErrorResponse
import com.woowa.data.remote.model.response.university.DepartmentDto
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
internal class UniversityServiceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var universityService: UniversityService

    @BeforeEach
    fun setUp() {
        mockWebServer = MockWebServer()
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        universityService = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(mockWebServer.url(""))
            .build()
            .create()
    }

    @Test
    fun `대학교를 검색 할 수 있다`() = runTest {
        //given
        val responseJson =
            File("src/test/java/resources/university/university_success.json").readText()
        val response = MockResponse()
            .setResponseCode(200)
            .setBody(responseJson)
        mockWebServer.enqueue(response)

        // when
        var actualResponse = universityService.searchUniversity("경")
        actualResponse =
            actualResponse.copy(data = actualResponse.data?.filter { univ -> univ.name.contains("경") })

        // then
        assertAll(
            { assertThat(actualResponse.success).isTrue() },
            { assertThat(actualResponse.data).hasSize(2) },
            { assertThat(actualResponse.error).isEqualTo(ErrorResponse.of()) },
        )
    }

    @Test
    fun `대학 학과를 검색할 수 있다`() = runTest {
        //given
        val responseJson =
            File("src/test/java/resources/university/department_success.json").readText()
        val response = MockResponse()
            .setResponseCode(200)
            .setBody(responseJson)
        mockWebServer.enqueue(response)

        // when
        val actualResponse = universityService.getUniversityDepartments(1L, "경")

        // then
        assertAll(
            { assertThat(actualResponse.success).isTrue() },
            { assertThat(actualResponse.data).hasSize(5) },
            { assertThat(actualResponse.data).contains(DepartmentDto(5L, "공과대학", "정보통신공학과")) },
            { assertThat(actualResponse.error).isEqualTo(ErrorResponse.of()) },
        )
    }
}