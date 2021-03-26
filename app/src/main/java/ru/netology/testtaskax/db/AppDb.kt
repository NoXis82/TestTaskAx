package ru.netology.testtaskax.db

import androidx.room.*
import ru.netology.testtaskax.dao.ICommentDao
import ru.netology.testtaskax.dto.CommentDto

@Database(entities = [CommentDto::class], version = 1)
abstract class AppDb : RoomDatabase() {
    abstract fun CommentDao(): ICommentDao
}