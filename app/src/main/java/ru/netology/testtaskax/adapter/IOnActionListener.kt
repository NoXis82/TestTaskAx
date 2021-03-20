package ru.netology.testtaskax.adapter

import ru.netology.testtaskax.dto.Comment

interface IOnActionListener {
    fun onClickComment(comment: Comment)
}