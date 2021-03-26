package ru.netology.testtaskax.di.components

import dagger.Component
import ru.netology.testtaskax.activity.MainActivity
import ru.netology.testtaskax.di.modules.NetworkModule
import ru.netology.testtaskax.di.modules.PreferencesModule
import ru.netology.testtaskax.di.modules.RoomModule
import ru.netology.testtaskax.di.modules.ServiceModule
import javax.inject.Singleton

@Component (modules = [ServiceModule::class, RoomModule::class, NetworkModule::class,PreferencesModule::class])
@Singleton
interface AppComponent {

    fun inject(mainActivity: MainActivity)
}