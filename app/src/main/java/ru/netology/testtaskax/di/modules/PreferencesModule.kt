package ru.netology.testtaskax.di.modules

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PreferencesModule {
    @Provides
    @Singleton
    fun provideSharedPreference(app: Application): SharedPreferences =
        app.getSharedPreferences("postId", Context.MODE_PRIVATE)
}