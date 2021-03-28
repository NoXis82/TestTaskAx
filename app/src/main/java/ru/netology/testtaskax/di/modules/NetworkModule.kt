package ru.netology.testtaskax.di.modules

import dagger.Module
import dagger.Provides
import ru.netology.testtaskax.api.ICommentsApiService
import ru.netology.testtaskax.dao.ICommentDao
import ru.netology.testtaskax.repository.CommentRepositoryImpl
import ru.netology.testtaskax.repository.ICommentRepository
import javax.inject.Singleton

@Module(includes = [ServiceModule::class, RoomModule::class])
class NetworkModule {

    @Provides
    @Singleton
    fun provideNetworkService(dao: ICommentDao, api: ICommentsApiService): ICommentRepository {
        return CommentRepositoryImpl(dao, api)
    }

}