package com.instabug.localprefs

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MyPreference @Inject constructor(@ApplicationContext context : Context){
    private val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    private val PREF_TAG="local pref"

    fun getData(): String {
        return prefs.getString(PREF_TAG, "")!!
    }
    fun saveData(query: String) {
        prefs.edit().putString(PREF_TAG, query).apply()
    }
}