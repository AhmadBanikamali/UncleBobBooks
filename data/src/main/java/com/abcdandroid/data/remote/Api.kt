package com.abcdandroid.data.remote

import retrofit2.http.GET
import retrofit2.http.Query


interface Api {

    @GET("volumes")
    suspend fun getBooks(@Query("q") author: String)


    companion object {
        const val BASE_URL = "https://www.googleapis.com/books/v1/"
    }
}