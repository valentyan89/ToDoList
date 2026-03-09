package com.example.todolist.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.todolist.presentation.screens.TodoDetailScreen
import com.example.todolist.presentation.screens.TodoListScreen

import com.example.todolist.presentation.viewmodel.TodoViewModel

@Composable
fun ApplicationNavGraph(navController: NavHostController, viewModel: TodoViewModel){
    NavHost(navController = navController, startDestination = "TodoList"){

        composable(route = "TodoList"){
            TodoListScreen(
                todoTasks = viewModel.todos,
                onTodoClick = {id -> navController.navigate(route = "Detail/$id")},
                onToggleClick = {id -> viewModel.toggleTodo(id)}
            )
        }

        composable(
            route = "Detail/{todoId}",
            arguments = listOf(navArgument("todoId") { type = NavType.IntType })
            ){ backStackEntry ->
            val todoId = backStackEntry.arguments?.getInt("todoId") ?: return@composable
            val todo = viewModel.todos.value.find{it.id == todoId}

            todo?.let {
                TodoDetailScreen(
                    todo = it,
                    onBackClick = {navController.popBackStack()}
                )
            }
        }
    }
}