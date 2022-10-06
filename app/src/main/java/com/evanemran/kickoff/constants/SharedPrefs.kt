package com.evanemran.kickoff.constants

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences


class SharedPrefs(var context: Context) {

    val SHARED_PREFS = "sharedPrefs"
    val TOKEN = "token"

    fun saveToken(token: String) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(TOKEN, token)
        editor.apply()
    }

    fun getToken(): String {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)
        val token = sharedPreferences.getString(TOKEN, "")
        return token!!
    }

}