package com.example.todolist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.todolist.domain.model.TodoItem
import com.example.todolist.domain.usecase.GetTodosUseCase
import com.example.todolist.domain.usecase.ToggleTodoUseCase
import kotlinx.coroutines.flow.MutableStateFlow

class TodoViewModel(
    private val getTodos: GetTodosUseCase,
    private val toggleTodos: ToggleTodoUseCase
) : ViewModel() {
    //
}