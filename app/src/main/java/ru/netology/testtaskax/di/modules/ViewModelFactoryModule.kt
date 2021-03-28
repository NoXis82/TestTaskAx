package ru.netology.testtaskax.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import ru.netology.testtaskax.di.factory.ViewModelFactory
import javax.inject.Provider
import javax.inject.Singleton

@Module
@Singleton
class ViewModelFactoryModule {

    @Provides
    @Singleton
    fun viewModelFactory(
        providerMap: Map<Class<out ViewModel>,
                Provider<ViewModel>>
    ): ViewModelProvider.Factory {
        return ViewModelFactory(providerMap)
    }
}