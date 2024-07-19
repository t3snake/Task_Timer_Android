package com.t3snake.tasktimer

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.RoutineScreen.route) {
        composable(route = Screen.RoutineScreen.route) {

        }
    }
}