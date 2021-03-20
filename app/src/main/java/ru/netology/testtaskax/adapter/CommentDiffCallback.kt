package ru.netology.testtaskax.adapter

import androidx.recyclerview.widget.DiffUtil
import ru.netology.testtaskax.dto.*

class CommentDiffCallback : DiffUtil.ItemCallback<CommentDto>() {
    override fun areItemsTheSame(oldItem: CommentDto, newItem: CommentDto): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CommentDto, newItem: CommentDto): Boolean {
        return oldItem == newItem
    }

}