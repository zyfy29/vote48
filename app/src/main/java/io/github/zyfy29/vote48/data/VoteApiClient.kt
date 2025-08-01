package io.github.zyfy29.vote48.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.logging.HttpLoggingInterceptor
import java.util.Base64

object VoteApiClient {
    private val BASE_URL = String(Base64.getDecoder().decode("aHR0cHM6Ly94b3g0OC50b3A="))

    @OptIn(ExperimentalSerializationApi::class)
    val apiService: VoteApiService by lazy {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY
        val contentType = "application/json".toMediaType()
        val client = OkHttpClient.Builder()
            .addInterceptor(logger) // ← loggerを追加
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .header(
                        "user-agent",
                        "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/138.0.0.0 Safari/537.36"
                    )
                    .build()
                chain.proceed(request)
            }
            .build()
        val json = Json { ignoreUnknownKeys = true }
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create(VoteApiService::class.java)
    }
}