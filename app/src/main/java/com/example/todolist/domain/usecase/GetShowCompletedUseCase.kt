package com.example.todolist.domain.usecase

import com.example.todolist.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow

class GetShowCompletedUseCase(private val repository: TodoRepository) {
    operator fun invoke(): Flow<Boolean> = repository.isEnabled()
}