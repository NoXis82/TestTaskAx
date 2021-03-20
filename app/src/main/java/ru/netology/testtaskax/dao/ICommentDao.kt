package ru.netology.testtaskax.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.netology.testtaskax.dto.CommentDto

@Dao
interface ICommentDao {

    @Query("SELECT * FROM CommentDto")
    fun getComments(): LiveData<List<CommentDto>>

    @Query("SELECT * FROM CommentDto")
    fun getList(): List<CommentDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(comment: CommentDto)

    @Query("UPDATE CommentDto SET body = :body, date = :date WHERE id = :id")
    suspend fun update(id: Int, body: String, date: Long)

    @Query("SELECT EXISTS(SELECT * FROM CommentDto WHERE id = :id)")
    fun isRowIsExist(id: Int): Boolean

    @Transaction
    suspend fun insertList(comments: List<CommentDto>) {
        comments.forEach {
            if (isRowIsExist(it.id) ) {
                update(it.id, it.body, it.date)
            } else {
                insert(it)
            }
        }
    }
}