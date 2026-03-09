package com.example.todolist.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController

import com.example.todolist.presentation.viewmodel.TodoViewModel

@Composable
fun ApplicationNavGraph(navController: NavHostController, viewModel: TodoViewModel){
    NavHost(
        navController = navController,
        startDestination = "TodoList",
        modifier = Modifier.background(Color(0xFFFFBDBD))
        ){
        composable(route = "TodoList"){

        }

        composable(route = "Detail"){

        }
    }
}