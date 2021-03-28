package ru.netology.testtaskax.sharedpref

interface IPreferencesHelper {
    fun getInt(): Int
    fun putInt(currentPostId: Int)
}