package ru.netology.testtaskax.api

import retrofit2.Response
import retrofit2.http.*
import ru.netology.testtaskax.dto.Comment

interface ICommentsApiService {
    @GET("comments?")
    suspend fun getAllComments(@Query("postId") id: Int): Response<List<Comment>>
}