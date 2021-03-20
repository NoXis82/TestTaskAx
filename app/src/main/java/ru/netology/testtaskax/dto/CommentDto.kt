package ru.netology.testtaskax.dto

import androidx.room.*
import java.util.*


@Entity
data class CommentDto(
    @PrimaryKey(autoGenerate = true)
    val localId: Long,
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String,
    val date: Long,
) {
    fun toDto() =
        Comment(
            postId,
            id,
            name,
            email,
            body,

        )
    companion object {
        fun fromDto(dto: Comment) = CommentDto(
            0,
            dto.postId,
            dto.id,
            dto.name,
            dto.email,
            dto.body,
            Calendar.getInstance().time.time
        )
    }

}



