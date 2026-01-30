package com.example.todolist.domain.model

data class TodoItem(
    val id: Int,
    val title: String,
    val description: String,
    val isCompleted: Boolean
)