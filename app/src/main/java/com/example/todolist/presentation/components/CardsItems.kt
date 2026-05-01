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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.todolist.domain.model.TodoItem
import com.example.todolist.presentation.viewmodel.TodoViewModel

@Composable
fun ItemCard(
    task: TodoItem,
    onClick: (Int) -> Unit,
    onCheckBoxState: (Int) -> Unit,
    showCompleted: Boolean = false
) {
    val flagLineThrough = when(showCompleted && task.isCompleted){
        true -> TextDecoration.LineThrough
        else -> TextDecoration.None
    }
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
                onCheckedChange = { onCheckBoxState(task.id) },
                modifier = Modifier.testTag("CheckBox")
            )

            Spacer(Modifier.padding(start = 12.dp))

            Column(modifier = Modifier.weight(1f)) {

                Text(
                    text = task.title,
                    fontWeight = FontWeight.SemiBold,
                    textDecoration = flagLineThrough
                )

                if (task.description.isNotBlank()) {
                    Text(
                        text = task.description,
                        textDecoration = flagLineThrough
                    )
                }
            }
        }
    }
}