package com.example.todolist.presentation.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import com.example.todolist.domain.model.TodoItem
import com.example.todolist.presentation.components.ItemCard

@Composable
fun TodoListScreen(
    todoTasks: State<List<TodoItem>>,
    onTodoClick: (Int) -> Unit,
    onToggleClick: (Int) -> Unit
){
    val todoList by todoTasks

    LazyColumn() {
        items(
            items = todoList,
            key = {todo -> todo.id}
        ){todo ->
            ItemCard(
                task = todo,
                onClick = { onTodoClick(todo.id)},
                onCheckBoxState = {onToggleClick(todo.id)}
            )
        }
    }
}