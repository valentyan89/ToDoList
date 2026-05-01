package com.example.todolist.domain.usecase

import com.example.todolist.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow

class ShowCompletedUseCase(private val repository: TodoRepository) {
    operator fun invoke(enabled: Boolean): Flow<Unit> = repository.showCompletedTodos(enabled)
}