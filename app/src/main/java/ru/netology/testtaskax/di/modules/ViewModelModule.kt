package ru.netology.testtaskax.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.netology.testtaskax.di.annotation.ViewModelKey
import ru.netology.testtaskax.di.factory.ViewModelFactory
import ru.netology.testtaskax.viewmodel.CommentViewModel


@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CommentViewModel::class)
    abstract fun bindCommentViewModel(commentViewModel: CommentViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factoryModule: ViewModelFactory): ViewModelProvider.Factory
}