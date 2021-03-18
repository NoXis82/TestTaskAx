package ru.netology.testtaskax

import android.app.Application
import ru.netology.testtaskax.repository.CommentRepositoryImpl
import ru.netology.testtaskax.repository.ICommentRepository

class App: Application() {

    companion object {
        lateinit var repository: ICommentRepository
    }

    override fun onCreate() {
        super.onCreate()
        repository = CommentRepositoryImpl()
       //     AppDb.getInstance(applicationContext)
        //        .ValuteDao()
       // )
    }

}