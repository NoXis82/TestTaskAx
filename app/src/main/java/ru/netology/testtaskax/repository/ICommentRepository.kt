package ru.netology.testtaskax.repository

import androidx.lifecycle.LiveData
import ru.netology.testtaskax.dto.CommentDto

interface ICommentRepository {
    val comments: LiveData<List<CommentDto>>
    suspend fun getAllComments(id: Int)
    suspend fun getList() : List<CommentDto>

}