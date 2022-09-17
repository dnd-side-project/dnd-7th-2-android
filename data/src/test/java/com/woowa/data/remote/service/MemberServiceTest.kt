package com.woowa.data.remote.service

import com.google.common.truth.Truth.assertThat
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.woowa.data.remote.model.response.ErrorResponse
import com.woowa.domain.model.code.Field
import com.woowa.domain.model.code.PersonalityAdjective
import com.woowa.domain.model.code.PersonalityNoun
import com.woowa.domain.model.member.Member
import com.woowa.domain.model.member.MemberEdit
import com.woowa.domain.model.member.Personality
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
internal class MemberServiceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var memberService: MemberService

    @BeforeEach
    fun setUp() {
        mockWebServer = MockWebServer()
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        memberService = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(mockWebServer.url(""))
            .build()
            .create()
    }

    @Test
    fun `회원 상세 정보를 조회 할 수 있다`() = runTest {
        // Given
        val responseJson =
            File("src/test/java/resources/member/member_search_success.json").readText()
        val response = MockResponse()
            .setResponseCode(200)
            .setBody(responseJson)
        mockWebServer.enqueue(response)

        // When
        val actualResponse = memberService.getMember(1)

        // Then
        val member = Member(
            id = 1,
            nickname = "테스트닉네임",
            personality = Personality("논리적인", "분석가"),
            departmentName = "테스트학과",
            interestingFields = listOf("IT/소프트웨어/게임", "디자인"),
            admissionYear = 2017,
            introduction = "테스트자기소개",
            introductionUrl = "test.com",
            level = 1,
            participationPct = 100.0,
            reviewTagToNums = hashMapOf(
                Pair("책임감 굿", 0),
                Pair("시간매너 끝판왕", 0),
                Pair("마감을 칼같이", 0),
                Pair("분위기 메이커", 0)
            ),
            numTotalEndProject = 0,
            numCompleteProject = 0
        )
        assertAll(
            { assertThat(actualResponse.success).isTrue() },
            { assertThat(actualResponse.data?.toMember()).isEqualTo(member) },
            { assertThat(actualResponse.error).isEqualTo(ErrorResponse.of()) }
        )
        println("Member Personality: ${actualResponse.data?.toMember()?.personality}")
        println("Member Interesting Fields: ${actualResponse.data?.toMember()?.interestingFields}")
        println("Member Review Tag: ${actualResponse.data?.toMember()?.reviewTagToNums}")
        println("${Field.values().filter { it.title == "광고/마케팅" || it.title == "금융/회계" }}")
    }

    @Test
    fun `회원 정보를 수정 할 수 있다`() = runTest {
        // Given
        val responseJson =
            File("src/test/java/resources/member/member_update_success.json").readText()
        val response = MockResponse()
            .setResponseCode(200)
            .setBody(responseJson)
        mockWebServer.enqueue(response)

        // When
        val actualResponse = memberService.editMember(
            MemberEdit(
                nickname = "테스트닉네임",
                personalityAdjective = PersonalityAdjective.LOGICAL.toString(),
                personalityNoun = PersonalityNoun.LEADER.toString(),
                interestingFields = listOf(Field.IT_SW_GAME.toString(), Field.PLANNING_IDEA.toString()),
                introduction = "자기소개",
                introductionUrl = "http://테스트-자기소개-링크.com"
            )
        )

        assertAll(
            { assertThat(actualResponse.success).isTrue() },
            { assertThat(actualResponse.data?.toMemberUpdate()?.id).isEqualTo(1) },
            { assertThat(actualResponse.error).isEqualTo(ErrorResponse.of()) }
        )
    }
}