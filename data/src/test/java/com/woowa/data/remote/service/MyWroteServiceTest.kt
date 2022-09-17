package com.woowa.data.remote.service

import com.google.common.truth.Truth
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.woowa.data.remote.model.response.ErrorResponse
import com.woowa.domain.model.code.Type
import com.woowa.domain.model.mywrote.MyWrote
import com.woowa.domain.model.mywrote.MyWroteContents
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
internal class MyWroteServiceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var myWroteService: MyWroteService

    @BeforeEach
    fun setUp() {
        mockWebServer = MockWebServer()
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        myWroteService = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(mockWebServer.url(""))
            .build()
            .create()
    }

    @Test
    fun `내가 쓴 글을 조회 할 수 있다`() = runTest {
        // Given
        val responseJson =
            File("src/test/java/resources/mywrote/my_wrote_success.json").readText()
        val response = MockResponse()
            .setResponseCode(200)
            .setBody(responseJson)
        mockWebServer.enqueue(response)

        // When
        val actualResponse = myWroteService.getMyWrote(1, 10, "IN_PROGRESS")

        // Then
        val myWrote = MyWrote(
            page = 0,
            perSize = 5,
            totalCount = 1,
            totalPages = 1,
            prev = false,
            next = true,
            contents = listOf(
                MyWroteContents(
                    id = 1,
                    title = "모집글 제목 테스트",
                    type = Type.LECTURE,
                    status = "IN_PROGRESS",
                    commentCount = 0,
                    bookmarkCount = 0,
                    projectName = "test-lecture-project-name",
                    professor = "test-professor-name",
                    field = null,
                    fieldCategory = null,
                    createdDate = "2022-08-21T13:00:00"
                ),
                MyWroteContents(
                    id = 2,
                    title = "모집글 제목 테스트2",
                    type = Type.SIDE,
                    status = "IN_PROGRESS",
                    commentCount = 0,
                    bookmarkCount = 0,
                    projectName = "test-lecture-project-name2",
                    professor = null,
                    field = "IT/소프트웨어/게임",
                    fieldCategory = "스터디",
                    createdDate = "2022-09-14T19:00:00"
                )
            )
        )
        assertAll(
            { Truth.assertThat(actualResponse.success).isTrue() },
            { Truth.assertThat(actualResponse.data?.toMyWrote()).isEqualTo(myWrote) },
            { Truth.assertThat(actualResponse.error).isEqualTo(ErrorResponse.of()) }
        )
        println("MyWrote Type: ${actualResponse.data?.toMyWrote()?.contents?.get(1)?.type}")
        println("MyWrote Field: ${actualResponse.data?.toMyWrote()?.contents?.get(1)?.field}")
        println("MyWrote Field Category: ${actualResponse.data?.toMyWrote()?.contents?.get(1)?.fieldCategory}")
    }
}