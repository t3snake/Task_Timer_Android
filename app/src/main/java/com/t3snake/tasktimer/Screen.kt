package com.t3snake.tasktimer

import kotlinx.serialization.Serializable

class Screen {
    @Serializable
    object Routines

    @Serializable
    data class Timer(val id: String)

    @Serializable
    data class Config(val id: String)
}


