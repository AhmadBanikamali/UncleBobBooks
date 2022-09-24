package com.android.data.remote.ws.socketio

import com.android.data.remote.ws.SocketService
import io.socket.client.Socket
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SocketIOSocketServiceImpl @Inject constructor(
    private val socket: Socket,
    private val scope: CoroutineScope,
) : SocketService {


    init {
        socket.connect().also {
            println("io connected")
        }

        socket.on("testerEvent") {
            scope.launch {
                channel.send(it[0] as String)
            }
        }

    }

    private val channel = Channel<String>()


    override fun sendData(message: String) {
        socket.emit("clientEvent",message)
    }

    override fun receiveDate(): Channel<String> = channel
}