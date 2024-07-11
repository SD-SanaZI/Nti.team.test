package com.example.ntiteamtest.data

import android.util.Log
import com.example.ntiteamtest.domain.ServerRepo
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.websocket.WebSockets
import java.io.Serializable
import io.ktor.server.application.*
import io.ktor.websocket.*
import java.time.Duration
import io.ktor.server.routing.routing
import io.ktor.server.netty.*
import io.ktor.server.websocket.pingPeriod
import io.ktor.server.websocket.timeout
import io.ktor.server.websocket.webSocket
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ServerRepoImpl() : ServerRepo {
    private var netty: NettyApplicationEngine? = null

    override suspend fun startServer(port: Int) {
        if (netty != null) netty = null
        netty = embeddedServer(Netty, port) {
            install(WebSockets) {
                pingPeriod = Duration.ofSeconds(60)
                timeout = Duration.ofSeconds(15)
                maxFrameSize = Long.MAX_VALUE
                masking = false
            }
        }
        withContext(Dispatchers.IO){
            try{ netty?.start() }
            catch (e: Exception){
                Log.e("Netty Exception", e.message.toString())
            }
        }
    }

    override suspend fun stopServer() {
        netty?.stop()
        netty = null
    }

    override suspend fun sendMsg(msg: Serializable) {
        netty?.application?.routing { webSocket("/") { send(msg.toString()) } }
    }

    override fun isServerStarted(): Boolean {
        return netty != null
    }

}