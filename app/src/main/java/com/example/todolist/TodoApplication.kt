package com.example.todolist

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.example.todolist.data.local.TodoDatabase
import com.example.todolist.data.local.TodoJsonDataSource
import com.example.todolist.data.preferences.ApplicationSettings
import com.example.todolist.data.repository.TaskRepositoryImpl
import com.example.todolist.domain.repository.TodoRepository


private const val PREFERENCES_NAME = "app_preferences"
private val Context.dataStore by preferencesDataStore(name = PREFERENCES_NAME)

class TodoApplication : Application() {
    lateinit var taskRepository: TodoRepository

    override fun onCreate() {
        super.onCreate()

        val settings = ApplicationSettings(dataStore)
        val database = TodoDatabase.getDatabase(this)
        val dataSource = TodoJsonDataSource(this)

        taskRepository = TaskRepositoryImpl(dataSource, database.todoDao(), settings)
    }
}