package com.example.todolist.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

@Composable
fun ApplicationNavGraph(navController: NavHostController, viewModel: TodoViewModel){
    val todoState by viewModel.todos.collectAsStateWithLifecycle()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {navController.navigate(route = "AddScreen")}) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "addition button")
            }
        }
    ) { paddingValues ->

        NavHost(navController = navController, startDestination = "TodoList", modifier = Modifier.padding(paddingValues)) {

            composable(route = "TodoList") {
                TodoListScreen(
                    todoTasks = todoState,
                    onTodoClick = { id -> navController.navigate(route = "Detail/$id") },
                    onToggleClick = { id -> viewModel.toggleTodo(id) }
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
                        }
                    )
                }
            }

            composable(route = "AddScreen") {
                AddTodoScreen(
                    onSaveClick = { title, desc ->
                        viewModel.addTodo(title, desc)
                        navController.popBackStack()
                    },
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }
}