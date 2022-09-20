package com.android.domain

import kotlinx.coroutines.channels.ReceiveChannel

interface Repository {
    fun sendData(message: String)

    fun receiveDate(): ReceiveChannel<String>

    suspend fun getEmployee(id:Int) : Result<Any>
}