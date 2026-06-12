package com.example.todolist.di

import com.example.todolist.data.repository.TaskRepositoryImpl
import com.example.todolist.domain.repository.TodoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindTodoRepository(impl: TaskRepositoryImpl): TodoRepository
}