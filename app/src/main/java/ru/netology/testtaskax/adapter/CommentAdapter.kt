package ru.netology.testtaskax.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.testtaskax.R
import ru.netology.testtaskax.databinding.CommentCardBinding
import ru.netology.testtaskax.dto.CommentDto
import java.util.*

class CommentAdapter(
    private val onActionListener: IOnActionListener
) : ListAdapter<CommentDto, CommentViewHolder>(CommentDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val commentView = CommentCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CommentViewHolder(commentView, onActionListener)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val comment = getItem(position)
        holder.bind(comment, holder.itemView.context)
    }
}

class CommentViewHolder(
    private val binding: CommentCardBinding,
    private val onActionListener: IOnActionListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(comment: CommentDto, context: Context) {
        binding.apply {
            tvPostId.text = context.getString(R.string.title_postId, comment.postId)
            tvNameUser.text = context.getString(R.string.title_name, comment.name)
            tvDate.text = context.getString(R.string.title_date, Date(comment.date).toString())
            binding.root.setOnClickListener {
                onActionListener.onClickComment(comment)
            }
        }
    }
}
