package com.example.timifront_end.data.source.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "token")

class TokenData(private val context: Context) {
    val TOKEN_STRING = stringPreferencesKey("token")

    fun readToken(): Flow<String> {
        return context.dataStore.data
            .map { preferences ->
                preferences[TOKEN_STRING] ?: ""
            }
    }

    suspend fun writeToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[TOKEN_STRING] = token
        }
    }
}