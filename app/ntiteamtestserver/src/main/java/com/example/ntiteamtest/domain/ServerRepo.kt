package com.example.ntiteamtest.domain

import java.io.Serializable

interface ServerRepo {
    suspend fun startServer(port: Int)
    suspend fun stopServer()
    suspend fun sendMsg(msg: Serializable)
    fun isServerStarted(): Boolean
}