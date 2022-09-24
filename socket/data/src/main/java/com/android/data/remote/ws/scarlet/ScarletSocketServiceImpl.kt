package com.android.data.remote.ws.scarlet

import com.android.data.remote.ws.SocketService
import kotlinx.coroutines.channels.Channel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ScarletSocketServiceImpl @Inject constructor(private val scarletSocketService: ScarletSocketService) :
    SocketService {

    override fun sendData(message: String) {
        scarletSocketService.sendData(message)
    }

    override fun receiveDate(): Channel<String> {
        return scarletSocketService.receiveDate() as Channel<String>
    }
}