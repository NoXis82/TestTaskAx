package ru.netology.testtaskax.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import ru.netology.testtaskax.api.ICommentsApiService
import ru.netology.testtaskax.dao.ICommentDao
import ru.netology.testtaskax.dto.CommentDto
import ru.netology.testtaskax.error.*
import java.io.IOException
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

    override suspend fun getAllComments(id: Int) {
        try {
            val response = api.getAllComments(id)
            if (!response.isSuccessful) {
                throw ApiError(response.code(), response.message())
            }
            val body = response.body() ?: throw ApiError(response.code(), response.message())
            dao.insertList(body.map(CommentDto.Companion::fromDto))
        } catch (e: IOException) {
            throw NetworkError
        } catch (e: Exception) {
            throw UnknownError
        }
    }

    override suspend fun getList(): List<CommentDto> = dao.getList()
}