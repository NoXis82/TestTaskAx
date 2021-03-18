package ru.netology.testtaskax.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.*
import ru.netology.testtaskax.dto.Comment
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://jsonplaceholder.typicode.com/"
private val client = OkHttpClient.Builder()
    .connectTimeout(30, TimeUnit.SECONDS)
    .build()
private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .client(client)
    .build()

interface CommentsApiService {
    @GET("comments?")
    suspend fun getAllComments(@Query("postId") id: Int): List<Comment>
}

object CommentApi {
    val retrofitService: CommentsApiService by lazy(retrofit::create)
}