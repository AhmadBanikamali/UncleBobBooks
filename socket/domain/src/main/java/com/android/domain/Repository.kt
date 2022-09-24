package com.android.domain

import kotlinx.coroutines.channels.Channel

interface Repository {
    fun sendData(message: String)

    fun receiveDate(): Channel<String>

    suspend fun getEmployee(id:Int) : Result<Any>
}