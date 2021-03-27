package ru.netology.testtaskax.repository

import android.content.Context
import ru.netology.testtaskax.sharedpref.IPreferencesHelper
import javax.inject.Inject

class PreferencesHelperImpl @Inject constructor(private val context: Context) : IPreferencesHelper {
    companion object {
        private const val PREFERENCE_KEY = "postId"
    }

    override fun getInt(): Int {
        val sharedPref = context.getSharedPreferences(PREFERENCE_KEY, Context.MODE_PRIVATE)
        return sharedPref.getInt(PREFERENCE_KEY, 0)
    }

    override fun putInt(currentPostId: Int) {
        val sharedPref = context.getSharedPreferences(PREFERENCE_KEY, Context.MODE_PRIVATE)
        sharedPref.edit().putInt(PREFERENCE_KEY, currentPostId).apply()
    }

}
