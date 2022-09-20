package com.android.data

import com.android.data.remote.rest.RestService
import com.android.data.remote.ws.SocketService
import com.android.domain.Repository
import kotlinx.coroutines.channels.ReceiveChannel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(
    private val socketService: SocketService,
    private val restService: RestService,
) : Repository {
    override fun sendData(message: String) = socketService.sendData(message)

    override fun receiveDate(): ReceiveChannel<String> = socketService.receiveDate()

    override suspend fun getEmployee(id: Int): Result<Any> = try {
        Result.success(restService.getEmployee(id))
    } catch (e: Exception) {
        Result.failure(e)
    }


}