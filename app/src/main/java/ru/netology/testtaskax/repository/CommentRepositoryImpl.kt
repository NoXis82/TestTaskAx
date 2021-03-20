package ru.netology.testtaskax.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import ru.netology.testtaskax.api.CommentApi
import ru.netology.testtaskax.dao.ICommentDao
import ru.netology.testtaskax.dto.CommentDto

class CommentRepositoryImpl(private val dao: ICommentDao) : ICommentRepository {

    override val comments: LiveData<List<CommentDto>>
        get() = dao.getComments().map { list ->
            list.sortedByDescending {
                it.date
            }
        }

    override suspend fun getAllComments(id: Int) {
        val commentApi = CommentApi.retrofitService.getAllComments(id)
        dao.insertList(commentApi.map(CommentDto.Companion::fromDto))
    }

    override suspend fun getList(): List<CommentDto> = dao.getList()

}