package ru.netology.testtaskax.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.netology.testtaskax.dao.ICommentDao
import ru.netology.testtaskax.db.AppDb
import javax.inject.Singleton

@Module
class RoomModule {

    @Singleton
    @Provides
    fun provideDataBase(context: Context) : AppDb {
      return  Room.databaseBuilder(context, AppDb::class.java, "task_app.db").build()
    }

    @Singleton
    @Provides
    fun provideUserDao(appDb: AppDb): ICommentDao{
        return appDb.CommentDao()
    }
}