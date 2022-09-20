package com.android.data.remote.rest

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitRestService {

    @GET("{id}")
    suspend fun getEmployee(@Path("id") id: Int): Response<Any>
}