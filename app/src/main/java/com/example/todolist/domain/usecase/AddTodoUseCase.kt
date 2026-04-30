package com.example.todolist.domain.usecase

import com.example.todolist.domain.model.TodoItem
import com.example.todolist.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow

class AddTodoUseCase(private val repository: TodoRepository) {
    operator fun invoke(todo: TodoItem): Flow<Unit> = repository.addTodo(todo)
}