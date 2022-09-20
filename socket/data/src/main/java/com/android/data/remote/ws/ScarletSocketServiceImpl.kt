package com.android.data.remote.ws

import kotlinx.coroutines.channels.ReceiveChannel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ScarletSocketServiceImpl @Inject constructor(private val scarletSocketService: ScarletSocketService) :
    SocketService {
    override fun sendData(message: String) {
        scarletSocketService.sendData(message)
    }

    override fun receiveDate(): ReceiveChannel<String> {
        return scarletSocketService.receiveDate()
    }
}