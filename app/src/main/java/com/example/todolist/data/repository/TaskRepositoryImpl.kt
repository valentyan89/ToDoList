package com.example.todolist.data.repository

import com.example.todolist.data.local.TodoJsonDataSource
import com.example.todolist.data.model.TodoItemDto
import com.example.todolist.domain.model.TodoItem
import com.example.todolist.domain.repository.TodoRepository

private fun TodoItemDto.parseToDomain() =
    TodoItem(
        id = id,
        title = title,
        description = description,
        isCompleted = isCompleted
    )

class TaskRepositoryImpl(
    private val jsonDataSource: TodoJsonDataSource
) : TodoRepository {

    private val todoParse = mutableListOf<TodoItem>()

    override suspend fun getTodos(): List<TodoItem> {
        if (todoParse.isEmpty()){
            todoParse.addAll(jsonDataSource.getTodos().map{ it.parseToDomain() })
        }
        return todoParse.toList()
    }

    override suspend fun toggleTodo(id: Int) {
        val indexOfId = todoParse.indexOfFirst { it.id == id }
        if (indexOfId > -1){
            todoParse[indexOfId] = todoParse[indexOfId].copy(
                isCompleted = !todoParse[indexOfId].isCompleted
            )
        }
    }
}