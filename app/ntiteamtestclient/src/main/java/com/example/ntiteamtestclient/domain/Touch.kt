package com.example.ntiteamtestclient.domain

import java.io.Serializable

data class Touch(
    val x: Float = 0f,
    val y: Float = 0f,
    val axisX: Float = 0f,
    val axisY: Float = 0f,
    val pressure: Float = 0f
): Serializable