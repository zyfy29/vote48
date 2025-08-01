package io.github.zyfy29.vote48.data

import io.github.zyfy29.vote48.schema.Response
import io.github.zyfy29.vote48.schema.IdolData
import io.github.zyfy29.vote48.schema.IdolFanData
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface VoteApiService {
    @FormUrlEncoded
    @POST("/Api/vote2024")
    suspend fun getIdolList(
        @Field("group") group: String = "",
        @Field("team") team: String = "",
        @Field("name") name: String = "",
        @Field("sbtype") sbtype: String = ""
    ): Response<IdolData>

    @FormUrlEncoded
    @POST("/Api/votefans")
    suspend fun getIdolFanList(
        @Field("user_id") userId: String,
    ): Response<IdolFanData>
}
