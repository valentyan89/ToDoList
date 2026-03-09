package com.example.todolist.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.todolist.domain.model.TodoItem
import com.example.todolist.presentation.viewmodel.TodoViewModel

@Composable
fun ItemCard(
    task: TodoItem,
    onClick: (Int) -> Unit,
    onCheckBoxState: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth()
            .clickable { onClick(task.id) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 16.dp)
        ) {
            Checkbox(
                checked = task.isCompleted,
                onCheckedChange = { onCheckBoxState(task.id) }
            )

            Spacer(Modifier.padding(start = 12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = task.title,
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    text = task.description
                )
            }
        }
    }
}