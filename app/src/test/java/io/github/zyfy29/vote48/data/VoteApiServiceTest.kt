package io.github.zyfy29.vote48.data

import kotlinx.coroutines.test.runTest
import org.junit.Test

class VoteApiServiceTest {
    @Test
    fun testVoteApi() = runTest {
        val response = VoteApiClient.apiService.getIdolList("", "", "", "")
        println(response)
//        assertThat(response.error, org.hamcrest.CoreMatchers.nullValue<String>())
    }

    @Test
    fun testFanIdolApi() = runTest {
        val response = VoteApiClient.apiService.getFanIdolList("106234391")
        println(response)
    }
}

