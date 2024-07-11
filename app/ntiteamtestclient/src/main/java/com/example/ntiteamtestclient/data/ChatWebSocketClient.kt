package com.example.ntiteamtestclient.data

import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.net.URI


class ChatWebSocketClient(serverUri: URI, private val messageListener: (String) -> Unit) : WebSocketClient(serverUri) {

    override fun onOpen(handshakedata: ServerHandshake?) {}

    override fun onClose(code: Int, reason: String?, remote: Boolean) {}

    override fun onMessage(message: String?) {
        messageListener.invoke(message ?: "")
    }

    override fun onError(ex: Exception?) {}
}