package com.example.todolist.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.domain.model.TodoItem
import com.example.todolist.domain.usecase.GetTodosUseCase
import com.example.todolist.domain.usecase.ToggleTodoUseCase
import kotlinx.coroutines.launch

class TodoViewModel(
    private val getTodos: GetTodosUseCase,
    private val toggleTodos: ToggleTodoUseCase
) : ViewModel() {
    private val statusTodos = mutableStateOf<List<TodoItem>>(emptyList())
    val todos: State<List<TodoItem>> = statusTodos

    init {
        loadTodos()
    }

    fun loadTodos() {
        viewModelScope.launch {
            statusTodos.value = getTodos()
        }
    }

    fun toggleTodo(id: Int) {
        viewModelScope.launch {
            toggleTodos(id)
            statusTodos.value = getTodos()
        }
    }

    fun getTodoById(id: Int): TodoItem? = statusTodos.value.firstOrNull { it.id == id }
}