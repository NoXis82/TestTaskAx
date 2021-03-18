package ru.netology.testtaskax.repository

import ru.netology.testtaskax.api.CommentApi
import ru.netology.testtaskax.dto.Comment

class CommentRepositoryImpl() : ICommentRepository {

    override suspend fun getAllComments(id: Int): List<Comment> {
        val commentApi = CommentApi.retrofitService.getAllComments(id)
        return commentApi
    }

}