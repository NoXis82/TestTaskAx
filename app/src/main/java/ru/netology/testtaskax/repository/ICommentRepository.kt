package ru.netology.testtaskax.repository

import androidx.lifecycle.LiveData
import ru.netology.testtaskax.dto.Comment
import ru.netology.testtaskax.dto.CommentDto

interface ICommentRepository {
    val comments: LiveData<List<CommentDto>>
    suspend fun getAllComments(id: Int): List<Comment>
    suspend fun getList() : List<CommentDto>
}