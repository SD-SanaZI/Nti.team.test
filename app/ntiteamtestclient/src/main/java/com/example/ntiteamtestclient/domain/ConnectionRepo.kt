package com.example.ntiteamtestclient.domain

interface ConnectionRepo {
    fun startServer(port: Int, host: String)
    fun stopServer()
    fun getTrack():List<Touch>
}