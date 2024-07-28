package com.t3snake.tasktimer

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import com.t3snake.tasktimer.Screen
import com.t3snake.tasktimer.ui.theme.TaskTim3rTheme

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Routines) {
        composable<Screen.Routines> {
            TaskTim3rTheme {
                TimerScaffold()
            }
        }
        composable<Screen.Config> {
            // TODO
        }
        composable<Screen.Timer> {
            // TODO
        }
    }
}