package com.android.data.remote.ws

import kotlinx.coroutines.channels.Channel

interface SocketService {
    fun sendData(message: String)

    fun receiveDate(): Channel<String>
}