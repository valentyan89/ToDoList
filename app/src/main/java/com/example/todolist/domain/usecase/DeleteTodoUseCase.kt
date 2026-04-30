package com.example.todolist.domain.usecase

import com.example.todolist.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow

class DeleteTodoUseCase(private val repository: TodoRepository) {
    operator fun invoke(id: Int): Flow<Unit> = repository.deleteTodo(id)
}