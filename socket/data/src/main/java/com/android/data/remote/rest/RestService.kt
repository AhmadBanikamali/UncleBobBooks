package com.android.data.remote.rest

import retrofit2.Response

interface RestService {
    suspend fun getEmployee(id: Int): Response<Any>
}