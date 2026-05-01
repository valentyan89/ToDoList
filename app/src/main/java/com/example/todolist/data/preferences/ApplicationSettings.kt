package com.example.todolist.data.preferences

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class ApplicationSettings(private val dataStore: DataStore<Preferences>) {

    private companion object {
        val IS_PARSED_JSON = booleanPreferencesKey("isParsedJson")
        val SHOW_COMPLETED = booleanPreferencesKey("showCompleted")
    }

    val isParsedJson: Flow<Boolean> = dataStore.data
        .catch {
            if (it is IOException) {
                Log.e("RRR", "Error reading preferences", it)
                // emit() - Собирает значение, переданное вышестоящим процессом.
                // Этот метод не является потокобезопасным и не должен
                // вызываться одновременно.
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences -> preferences[IS_PARSED_JSON] ?: false }

    suspend fun savePreferences(isParsedJson: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_PARSED_JSON] = isParsedJson
        }
    }

    val isShowCompleted: Flow<Boolean> = dataStore.data
        .catch {
            if (it is IOException) {
                Log.e("RRR", "Error reading preferences", it)
                // emit() - Собирает значение, переданное вышестоящим процессом.
                // Этот метод не является потокобезопасным и не должен
                // вызываться одновременно.
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences -> preferences[SHOW_COMPLETED] ?: false }

    suspend fun saveShowCompleted(enabled: Boolean){
        dataStore.edit { preferences ->
            preferences[SHOW_COMPLETED] = enabled
        }
    }
}