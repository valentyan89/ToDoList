package com.example.todolist.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolist.domain.model.TodoItem

@Composable
fun TodoDetailScreen(
    todo: TodoItem,
    onBackClick: () -> Unit,
    onDeleteClick: () -> Unit,
    padding: PaddingValues
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(start = 24.dp, end = 24.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Backward"
                )
            }

            Spacer(Modifier.weight(1f))

            IconButton(onClick = onDeleteClick) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete"
                )
            }
        }

        Spacer(Modifier.height(14.dp))

        Text(
            text = todo.title,
            fontSize = 32.sp,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = todo.description,
            fontSize = 22.sp,
        )

        Spacer(modifier = Modifier.height(8.dp))

        val (statusText, statusColor) = when(todo.isCompleted){
            true -> "Completed" to Color(0xFF00FF9F)
            false -> "unCompleted" to Color(0xFFFF6EAE)
        }

        Text(
            text = statusText,
            fontSize = 22.sp,
            color = statusColor
        )
    }
}