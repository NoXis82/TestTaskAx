package ru.netology.testtaskax

import android.app.Application
import ru.netology.testtaskax.db.AppDb
import ru.netology.testtaskax.di.components.AppComponent
import ru.netology.testtaskax.di.components.DaggerAppComponent


class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent // Dagger 2
        private var appDb: AppDb? = null
    }

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() {
        appComponent = DaggerAppComponent.builder()
            .build()
    }

}