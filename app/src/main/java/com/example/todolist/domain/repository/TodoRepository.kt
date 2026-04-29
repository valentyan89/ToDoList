package com.example.todolist.domain.repository

import com.example.todolist.domain.model.TodoItem
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    fun getTodos(): Flow<List<TodoItem>>
    suspend fun toggleTodo(id: Int)
    suspend fun insertAll()
    suspend fun addTodo(todo: TodoItem): Flow<Unit>
}