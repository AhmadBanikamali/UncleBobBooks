package com.android.data.remote.ws.websocket

import com.android.data.remote.ws.SocketService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import okhttp3.*
import okio.ByteString
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WebSocketServiceImpl @Inject constructor(
    private val okHttpClient: OkHttpClient,
    private val request: Request,
    private val coroutineScope: CoroutineScope,
) : SocketService, WebSocketListener() {

    private lateinit var webSocket: WebSocket
    private var messageChannel = Channel<String>()

    init {
        okHttpClient.newWebSocket(request, this)
    }

    override fun sendData(message: String) {
        webSocket.send(message)
    }

    override fun receiveDate(): Channel<String> = messageChannel

    override fun onOpen(webSocket: WebSocket, response: Response) {
        super.onOpen(webSocket, response)
        this.webSocket = webSocket
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        super.onMessage(webSocket, text)
        coroutineScope.launch {
            messageChannel.send(text)
        }
    }

    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosed(webSocket, code, reason)
        println("onClosed")
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosing(webSocket, code, reason)
        println("onClosing")
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        super.onFailure(webSocket, t, response)
        println("onFailure")
    }

    override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
        super.onMessage(webSocket, bytes)
        println("onMessage")
    }
}