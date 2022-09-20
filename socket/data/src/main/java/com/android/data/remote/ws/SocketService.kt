package com.android.data.remote.ws

import io.reactivex.Flowable
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.Flow

interface SocketService {
    fun sendData(message: String)

    fun receiveDate(): ReceiveChannel<String>
}