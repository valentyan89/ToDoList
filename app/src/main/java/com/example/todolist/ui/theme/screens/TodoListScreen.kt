package com.example.todolist.ui.theme.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todolist.domain.model.TodoItem


@Composable
fun HomeScreen(
    todos: State<List<TodoItem>>,
    onToggleClick: (Int) -> Unit,
    onTodoClick: (Int) -> Unit
){
    val todoList by todos
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(items = todoList){
            item ->
                TodoItemCard(
                    todo = item.id,
                    onClick = { inTodoClick(item.id)},

                )
        }

    }
}