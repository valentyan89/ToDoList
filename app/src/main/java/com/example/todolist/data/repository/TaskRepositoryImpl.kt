package com.example.todolist.data.repository

import com.example.todolist.data.local.TodoJsonDataSource
import com.example.todolist.data.local.dao.TodoDao
import com.example.todolist.data.model.toEntity
import com.example.todolist.data.model.TodoItemDto
import com.example.todolist.data.model.toDomain
import com.example.todolist.data.preferences.ApplicationSettings
import com.example.todolist.domain.model.TodoItem
import com.example.todolist.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

private fun TodoItemDto.parseToDomain() =
    TodoItem(
        id = id,
        title = title,
        description = description,
        isCompleted = isCompleted
    )

class TaskRepositoryImpl(
    private val jsonDataSource: TodoJsonDataSource,
    private val todoDao: TodoDao,
    private val settings: ApplicationSettings
) : TodoRepository {

    override fun getTodos(): Flow<List<TodoItem>> {
        return todoDao.getAllTodos().map { entity ->
            entity.map { it.toDomain() }
        }
    }

    override fun toggleTodo(id: Int): Flow<Unit> = flow {
        val currentTodo = todoDao.getTodoById(id)
        currentTodo.let {
            todoDao.insert(it.copy(isCompleted = !it.isCompleted))
        }
        emit(Unit)
    }

    override fun insertAll(): Flow<Unit> = flow{
        TODO("KOLBASA")
        emit(Unit)
    }

    override suspend fun deleteTodo(id: Int): Flow<Unit> = flow {
        todoDao.delete(id)
        emit(Unit)
    }

    override fun addTodo(todo: TodoItem): Flow<Unit> = flow {
        todoDao.insert(todo.toEntity().copy(id = 0))
        emit(Unit)
    }
}