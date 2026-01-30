package com.example.todolist.data.repository

import com.example.todolist.domain.model.TodoItem
import com.example.todolist.domain.repository.TodoRepository

class TaskRepositoryImpl() : TodoRepository {
    override suspend fun getTodos(): List<TodoItem> {
        TODO("Not yet implemented")
    }

    override suspend fun toggleTodo(id: Int) {
        TODO("Not yet implemented")
    }
}