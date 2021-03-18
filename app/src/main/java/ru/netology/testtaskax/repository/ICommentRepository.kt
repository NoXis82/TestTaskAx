package ru.netology.testtaskax.repository

import ru.netology.testtaskax.dto.Comment

interface ICommentRepository {

    suspend fun getAllComments(id: Int): List<Comment>

}