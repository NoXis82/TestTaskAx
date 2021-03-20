package ru.netology.testtaskax.adapter

import ru.netology.testtaskax.dto.*

interface IOnActionListener {
    fun onClickComment(comment: CommentDto)
}