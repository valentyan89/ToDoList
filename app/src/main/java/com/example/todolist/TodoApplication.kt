package com.example.todolist

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.example.todolist.data.local.TodoDatabase
import com.example.todolist.data.local.TodoJsonDataSource
import com.example.todolist.data.preferences.ApplicationSettings
import com.example.todolist.data.repository.TaskRepositoryImpl
import com.example.todolist.domain.repository.TodoRepository
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TodoApplication : Application()