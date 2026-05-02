package com.example.todolist.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.todolist.presentation.screens.AddTodoScreen
import com.example.todolist.presentation.screens.TodoDetailScreen
import com.example.todolist.presentation.screens.TodoListScreen

import com.example.todolist.presentation.viewmodel.TodoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplicationNavGraph(navController: NavHostController, viewModel: TodoViewModel){
    val todoState by viewModel.todos.collectAsStateWithLifecycle()
    val showCompleted by viewModel.isCompletedEnabled.collectAsStateWithLifecycle()
    Scaffold(

        floatingActionButton = {
            FloatingActionButton(onClick = {navController.navigate(route = "AddScreen")}) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "addition button")
            }
        },
        topBar = {
            TopAppBar(
                title = {Text(text = "TodoList", style = MaterialTheme.typography.titleLarge)},
                actions = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text("Завершенные", style = MaterialTheme.typography.titleMedium)

                        Spacer(Modifier.width(14.dp))

                        Switch(
                            checked = showCompleted,
                            onCheckedChange = { viewModel.toggleCompleted(it) }
                        )
                    }

                }
            )
        }
    ) { paddingValues ->

        NavHost(navController = navController, startDestination = "TodoList") {

            composable(route = "TodoList") {
                TodoListScreen(
                    todoTasks = todoState,
                    onTodoClick = { id -> navController.navigate(route = "Detail/$id") },
                    onToggleClick = { id -> viewModel.toggleTodo(id) },
                    showCompleted = showCompleted,
                    padding = paddingValues
                )
            }

            composable(
                route = "Detail/{todoId}",
                arguments = listOf(navArgument("todoId") { type = NavType.IntType })
            ) { backStackEntry ->
                val todoId = backStackEntry.arguments?.getInt("todoId") ?: return@composable
                val todo = todoState.find { it.id == todoId }

                todo?.let {
                    TodoDetailScreen(
                        todo = it,
                        onBackClick = { navController.popBackStack() },
                        onDeleteClick = {
                            viewModel.deleteTodo(it.id)
                            navController.popBackStack()
                        },
                        padding = paddingValues
                    )
                }
            }

            composable(route = "AddScreen") {
                AddTodoScreen(
                    onSaveClick = { title, desc ->
                        viewModel.addTodo(title, desc)
                        navController.popBackStack()
                    },
                    onBackClick = { navController.popBackStack() },
                    padding = paddingValues
                )
            }
        }
    }
}