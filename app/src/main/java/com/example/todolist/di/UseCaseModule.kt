package com.example.todolist.di

import com.example.todolist.domain.repository.TodoRepository
import com.example.todolist.domain.usecase.AddAllTodosUseCase
import com.example.todolist.domain.usecase.AddTodoUseCase
import com.example.todolist.domain.usecase.DeleteTodoUseCase
import com.example.todolist.domain.usecase.GetShowCompletedUseCase
import com.example.todolist.domain.usecase.GetTodosUseCase
import com.example.todolist.domain.usecase.ShowCompletedUseCase
import com.example.todolist.domain.usecase.ToggleTodoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    @Provides
    fun addAll(repository: TodoRepository): AddAllTodosUseCase = AddAllTodosUseCase(repository)

    @Provides
    fun addTodo(repository: TodoRepository): AddTodoUseCase = AddTodoUseCase(repository)

    @Provides
    fun deleteTodo(repository: TodoRepository): DeleteTodoUseCase = DeleteTodoUseCase(repository)

    @Provides
    fun getShow(repository: TodoRepository): GetShowCompletedUseCase = GetShowCompletedUseCase(repository)

    @Provides
    fun getTodos(repository: TodoRepository): GetTodosUseCase = GetTodosUseCase(repository)

    @Provides
    fun showCompleted(repository: TodoRepository): ShowCompletedUseCase = ShowCompletedUseCase(repository)

    @Provides
    fun toggleTodo(repository: TodoRepository): ToggleTodoUseCase = ToggleTodoUseCase(repository)
}