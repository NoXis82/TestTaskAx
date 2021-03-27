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
import ru.netology.testtaskax.repository.ICommentRepository
import javax.inject.Inject
import kotlin.coroutines.EmptyCoroutineContext

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupActionBarWithNavController(findNavController(R.id.nav_host_fragment_container))
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_container)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}