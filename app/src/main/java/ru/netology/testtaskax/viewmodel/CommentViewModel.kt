package ru.netology.testtaskax.viewmodel

import android.app.Application
import android.os.CountDownTimer
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.netology.testtaskax.App
import ru.netology.testtaskax.dto.Comment

class CommentViewModel(application: Application) : AndroidViewModel(application) {
    private val LIMIT_ID = 3 //32
    private val PREFERENCE_KEY = "postId"
    private var currentPostId = 0
    private val preferences = getApplication<Application>()
        .getSharedPreferences(PREFERENCE_KEY, Application.MODE_PRIVATE)

    private val repository = App.repository
    private val _timer = MutableLiveData<Long>()
    val timer: LiveData<Long>
        get() = _timer


    private val _data = MutableLiveData<List<Comment>>()
    val data: LiveData<List<Comment>>
        get() = _data

    init {
        load()
    }

    private fun load() {
        incAndPrefPostId()
        viewModelScope.launch {
            _data.value = repository.getAllComments(id = currentPostId)
        }
        timer()
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
        val timer = object : CountDownTimer(10_000, 1000) {
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