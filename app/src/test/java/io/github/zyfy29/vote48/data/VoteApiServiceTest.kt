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
}

