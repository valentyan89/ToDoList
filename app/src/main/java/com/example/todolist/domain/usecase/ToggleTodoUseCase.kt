package com.example.todolist.domain.usecase

import com.example.todolist.domain.model.TodoItem
import com.example.todolist.domain.repository.TodoRepository

class ToggleTodoUseCase(private val repository: TodoRepository) {
    operator fun invoke(id: Int) = repository.toggleTodo(id)
}