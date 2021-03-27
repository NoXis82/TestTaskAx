package ru.netology.testtaskax.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map

import ru.netology.testtaskax.api.ICommentsApiService
import ru.netology.testtaskax.dao.ICommentDao
import ru.netology.testtaskax.dto.Comment
import ru.netology.testtaskax.dto.CommentDto
import javax.inject.Singleton

@Singleton
class CommentRepositoryImpl(
    private val dao: ICommentDao,
    private val api: ICommentsApiService
) : ICommentRepository {

    override val comments: LiveData<List<CommentDto>>
        get() = dao.getComments().map { list ->
            list.sortedByDescending {
                it.date
            }
        }

    override suspend fun getAllComments(id: Int): List<Comment> {
        val commentApi = api.getAllComments(id)
        dao.insertList(commentApi.map(CommentDto.Companion::fromDto))
        return commentApi
    }

    override suspend fun getList(): List<CommentDto> = dao.getList()

}