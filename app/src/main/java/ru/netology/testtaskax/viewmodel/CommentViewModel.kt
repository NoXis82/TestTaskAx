package ru.netology.testtaskax.viewmodel

import android.app.Application
import android.content.Intent
import android.net.Uri
import android.os.CountDownTimer
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import ru.netology.testtaskax.App
import ru.netology.testtaskax.R
import ru.netology.testtaskax.dto.CommentDto
import ru.netology.testtaskax.fragments.WidgetFragmentDirections
import java.io.IOException

class CommentViewModel(application: Application) : AndroidViewModel(application) {
    private val LIMIT_ID = 32
    private val PREFERENCE_KEY = "postId"
    private var currentPostId = 0
    private val preferences = getApplication<Application>()
        .getSharedPreferences(PREFERENCE_KEY, Application.MODE_PRIVATE)
    private var _oldList = mutableListOf<CommentDto>()
    private val repository = App.repository
    private val _timer = MutableLiveData<Long>()
    val timer: LiveData<Long>
        get() = _timer
    val data: LiveData<List<CommentDto>>
        get() = repository.comments
    private val _state = MutableLiveData(false)
    val state: LiveData<Boolean>
        get() = _state


    init {
        load()
    }

    private fun load() {
        _state.value = true
        incAndPrefPostId()
        viewModelScope.launch {
            try {
                _oldList = _oldList.union(repository.getList()).toMutableList()
                repository.getAllComments(id = currentPostId)
                _state.value = false
            } catch (e: IOException) {
                e.printStackTrace()
                Toast.makeText(
                    getApplication<Application>().applicationContext,
                    R.string.error_connect,
                    Toast.LENGTH_SHORT
                ).show()
                _state.value = false
            }
        }
        timer()
    }

    fun onClickEmail(toEmail: String) {
        val intent = Intent().apply {
            action = Intent.ACTION_SENDTO
            data = Uri.parse("mailto: $toEmail")
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        getApplication<Application>().startActivity(intent)
    }

    fun clickComment(commentDto: CommentDto, navController: NavController) {
        val action = WidgetFragmentDirections.actionWidgetFragmentToCommentViewFragment(
            postId = commentDto.postId,
            id = commentDto.id,
            name = commentDto.name,
            email = commentDto.email,
            body = commentDto.body,
            date = commentDto.date
        )
        navController.navigate(action)
    }

    fun equalsLists(dataList: List<CommentDto>): List<CommentDto> {
        if (!dataList.containsAll(_oldList)) {
            Toast.makeText(
                getApplication<Application>().applicationContext,
                R.string.title_update,
                Toast.LENGTH_SHORT
            ).show()
        }
        return dataList
    }


    private fun incAndPrefPostId() {
        val prefValue = preferences.getInt(PREFERENCE_KEY, 0)
        currentPostId = if (prefValue == LIMIT_ID) {
            1
        } else {
            prefValue + 1
        }
        preferences.edit().putInt(PREFERENCE_KEY, currentPostId).apply()
    }

    private fun timer() {
        val timer = object : CountDownTimer(60_000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _timer.value = millisUntilFinished / 1000
            }

            override fun onFinish() {
                load()
            }
        }
        timer.start()
    }

}