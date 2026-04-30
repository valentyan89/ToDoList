package com.example.todolist.domain.repository

import com.example.todolist.domain.model.TodoItem
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    fun getTodos(): Flow<List<TodoItem>>
    fun toggleTodo(id: Int): Flow<Unit>
    fun insertAll(): Flow<Unit>
    fun deleteTodo(id: Int): Flow<Unit>
    fun addTodo(todo: TodoItem): Flow<Unit>
}