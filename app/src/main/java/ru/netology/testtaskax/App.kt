package ru.netology.testtaskax

import android.app.Application
import ru.netology.testtaskax.db.AppDb
import ru.netology.testtaskax.di.components.AppComponent
import ru.netology.testtaskax.di.components.DaggerAppComponent
import ru.netology.testtaskax.di.modules.AppModule


class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initDagger(this)
    }

    private fun initDagger(application: Application) {
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(application))
            .build()
    }

}