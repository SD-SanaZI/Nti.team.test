package com.example.ntiteamtestclient.data

import com.example.ntiteamtestclient.domain.ConnectionRepo
import com.example.ntiteamtestclient.domain.Touch
import com.google.gson.Gson
import java.net.URI

class ConnectionRepoImpl(
    private val db: TouchDB,
    private val touchExecutor: TouchExecutor
) : ConnectionRepo {
    private var webSocketClient: ChatWebSocketClient? = null

    override fun startServer(port: Int, host: String) {
        val serverUri = URI("ws://$host:$port")
        db.clear()
        if(webSocketClient?.isOpen == true) webSocketClient?.close()
        webSocketClient = ChatWebSocketClient(serverUri) { message ->
            val touch = Gson().fromJson(message, Touch::class.java)
            touchExecutor.execute(touch)
            db.saveTouch(touch)
        }
        webSocketClient?.connect()
    }

    override fun stopServer() {
        webSocketClient?.close()
        webSocketClient = null
    }

    override fun getTrack(): List<Touch> {
        return db.getTrack()
    }
}