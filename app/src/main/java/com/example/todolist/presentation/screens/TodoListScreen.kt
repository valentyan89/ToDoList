package com.example.todolist.presentation.screens

import androidx.compose.foundation.layout.PaddingValues
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
import com.example.todolist.presentation.components.ItemCard

@Composable
fun TodoListScreen(
    todoTasks: List<TodoItem>,
    onTodoClick: (Int) -> Unit,
    onToggleClick: (Int) -> Unit,
    showCompleted: Boolean,
    padding: PaddingValues
){
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        items(
            items = todoTasks,
            key = { todo -> todo.id}
        ){todo ->
            ItemCard(
                task = todo,
                onClick = { onTodoClick(todo.id)},
                onCheckBoxState = {onToggleClick(todo.id)},
                showCompleted = showCompleted
            )
        }
    }
}