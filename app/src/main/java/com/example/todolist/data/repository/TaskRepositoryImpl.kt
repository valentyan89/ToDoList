package com.example.todolist.data.repository

import com.example.todolist.data.local.TodoJsonDataSource
import com.example.todolist.data.local.dao.TodoDao
import com.example.todolist.data.model.toEntity
import com.example.todolist.data.model.TodoItemDto
import com.example.todolist.data.model.toDomain
import com.example.todolist.domain.model.TodoItem
import com.example.todolist.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow
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
    private val todoDao: TodoDao
) : TodoRepository {

    override fun getTodos(): Flow<List<TodoItem>> {
        return todoDao.getAllTodos().map { it ->
            it.map { it.toDomain() }
        }
    }

    override suspend fun toggleTodo(id: Int) {
        TODO("Not yet implemented")
    }

//    override suspend fun toggleTodo(id: Int) {
//        val indexOfId = todoParse.indexOfFirst { it.id == id }
//        if (indexOfId > -1){
//            todoParse[indexOfId] = todoParse[indexOfId].copy(
//                isCompleted = !todoParse[indexOfId].isCompleted
//            )
//        }
//    }

    override suspend fun insertAll() {
        val entities = jsonDataSource.getTodos().map { it.toEntity() }
        todoDao.insertAll(entities)
    }

    override suspend fun addTodo(todo: TodoItem): Flow<Unit> = flow {
        todoDao.insert(todo.toEntity().copy(id = 0))
    }
}