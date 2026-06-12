package com.example.todolist.data.local

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.example.todolist.data.model.TodoItemDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TodoJsonDataSource @Inject constructor(private val context: Context, private val gson: Gson) {
    fun getTodos(): List<TodoItemDto> {
        val json = context.assets.open("todos.json").bufferedReader().use { it.readText() }
        val type = object : TypeToken<List<TodoItemDto>>() {}.type
        return gson.fromJson(json, type)
    }
}