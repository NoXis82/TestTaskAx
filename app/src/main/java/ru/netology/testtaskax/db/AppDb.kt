package ru.netology.testtaskax.db

import android.content.Context
import androidx.room.*
import ru.netology.testtaskax.dao.ICommentDao
import ru.netology.testtaskax.dto.CommentDto

@Database(entities = [CommentDto::class], version = 1)
abstract class AppDb : RoomDatabase() {
    abstract fun CommentDao(): ICommentDao

    companion object {
        @Volatile
        private var instance: AppDb? = null
        fun getInstance(context: Context): AppDb {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, AppDb::class.java, "task_app.db")
                .allowMainThreadQueries()
                .build()
    }

}