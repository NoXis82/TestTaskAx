package ru.netology.testtaskax.di.components

import dagger.Component
import ru.netology.testtaskax.activity.MainActivity
import ru.netology.testtaskax.di.modules.*
import ru.netology.testtaskax.fragments.WidgetFragment
import javax.inject.Singleton

@Component(modules = [ServiceModule::class, RoomModule::class, NetworkModule::class,
    PreferencesModule::class, AppModule::class, ViewModelModule::class])
@Singleton
interface AppComponent {
    fun inject(widgetFragment: WidgetFragment)
}