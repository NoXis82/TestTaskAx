package ru.netology.testtaskax.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.netology.testtaskax.repository.PreferencesHelperImpl
import ru.netology.testtaskax.sharedpref.IPreferencesHelper
import javax.inject.Singleton

@Module
@Singleton
class PreferencesModule {

    @Provides
    @Singleton
    fun providePreferencesHelper(context: Context): IPreferencesHelper {
        return PreferencesHelperImpl(context)
    }

}