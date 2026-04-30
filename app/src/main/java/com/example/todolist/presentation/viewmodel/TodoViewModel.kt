package com.example.todolist.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.todolist.TodoApplication
import com.example.todolist.domain.model.TodoItem
import com.example.todolist.domain.usecase.AddAllTodosUseCase
import com.example.todolist.domain.usecase.AddTodoUseCase
import com.example.todolist.domain.usecase.DeleteTodoUseCase
import com.example.todolist.domain.usecase.GetTodosUseCase
import com.example.todolist.domain.usecase.ToggleTodoUseCase
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.stateIn
import kotlin.collections.emptyList
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY

class TodoViewModel(
    private val getTodos: GetTodosUseCase,
    private val toggleTodos: ToggleTodoUseCase,
    private val addTodos: AddTodoUseCase,
    private val deleteTodos: DeleteTodoUseCase,
    private val addAllTodos: AddAllTodosUseCase
) : ViewModel() {
    val todos: StateFlow<List<TodoItem>> = getTodos().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    init {
        addAllTodos().launchIn(viewModelScope)
    }

    fun addTodo(title: String, desc: String = ""){
        val todo = TodoItem(id = 0, title, desc, false)
        addTodos(todo).launchIn(viewModelScope)
    }

    fun toggleTodo(id: Int) {
        toggleTodos(id).launchIn(viewModelScope)
    }

    fun deleteTodo(id: Int){
        deleteTodos(id).launchIn(viewModelScope)
    }

//    fun getListTodos(){
//        getTodos().launchIn(viewModelScope)
//    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as TodoApplication)
                val repo = application.taskRepository

                TodoViewModel(
                    getTodos = GetTodosUseCase(repo),
                    toggleTodos = ToggleTodoUseCase(repo),
                    addTodos = AddTodoUseCase(repo),
                    deleteTodos = DeleteTodoUseCase(repo),
                    addAllTodos = AddAllTodosUseCase(repo)
                )
            }
        }
    }
}