package ru.netology.testtaskax.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ru.netology.testtaskax.App
import ru.netology.testtaskax.R
import ru.netology.testtaskax.api.ICommentsApiService
import ru.netology.testtaskax.di.modules.ServiceModule
import javax.inject.Inject
import kotlin.coroutines.EmptyCoroutineContext

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var apiService: ICommentsApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        App.appComponent.inject(this)
        CoroutineScope(EmptyCoroutineContext).launch {
        val test =  apiService.getAllComments(2)
        Log.e("MY_TAG", test.toString())
        }
        setupActionBarWithNavController(findNavController(R.id.nav_host_fragment_container))
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_container)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}