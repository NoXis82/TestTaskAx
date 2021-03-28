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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.netology.testtaskax.R
import ru.netology.testtaskax.dto.CommentDto
import ru.netology.testtaskax.dto.State
import ru.netology.testtaskax.fragments.WidgetFragmentDirections
import ru.netology.testtaskax.repository.ICommentRepository
import ru.netology.testtaskax.sharedpref.IPreferencesHelper
import javax.inject.Inject

class CommentViewModel @Inject constructor(
    app: Application,
    private val repository: ICommentRepository,
    private val preferences: IPreferencesHelper
) : AndroidViewModel(app) {
    companion object {
        private const val LIMIT_ID = 32
    }

    private var currentPostId = 0
    private var _oldList = mutableListOf<CommentDto>()
    private val _timer = MutableLiveData<Long>()
    val timer: LiveData<Long>
        get() = _timer
    val data: LiveData<List<CommentDto>>
        get() = repository.comments
    private val _dataState = MutableLiveData<State>()
    val dataState: LiveData<State>
        get() = _dataState

    init {
        load()
    }

    fun load() {
        _dataState.value = State(loading = true)
        incAndPrefPostId()
        viewModelScope.launch {
            try {
                _oldList.clear()
                withContext(Dispatchers.IO) {
                    _oldList = repository.getList().toMutableList()
                }
                repository.getAllComments(id = currentPostId)
                _dataState.value = State()
            } catch (e: Exception) {
                _dataState.value = State(error = true)
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
        val prefValue = preferences.getInt()
        currentPostId = if (prefValue == LIMIT_ID) {
            1
        } else {
            prefValue + 1
        }
        preferences.putInt(currentPostId)
    }

    private fun timer() {
        val timer = object : CountDownTimer(60_000, 1_000) {
            override fun onTick(millisUntilFinished: Long) {
                _timer.value = millisUntilFinished / 1_000
            }

            override fun onFinish() {
                load()
            }
        }
        timer.start()
    }

}