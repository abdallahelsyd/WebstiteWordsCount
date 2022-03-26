package com.instabug.localprefs.general

import kotlinx.coroutines.flow.Flow

interface IPrefsStore {
    fun getString(): Flow<String>
    suspend fun saveString(strToSave:String)
}