package com.t3snake.tasktimer

sealed class Screen (val route: String) {
    object RoutineScreen : Screen("routine_screen")
    object TimerScreen : Screen("timer_screen")
    object ConfigScreen : Screen("config_screen")
}