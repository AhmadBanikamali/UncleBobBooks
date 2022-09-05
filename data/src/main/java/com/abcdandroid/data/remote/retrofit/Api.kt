package com.abcdandroid.data.remote.retrofit

import com.abcdandroid.data.remote.model.BooksResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface Api {

    @GET("volumes")
    suspend fun getBooks(@Query("q") author: String): BooksResponse

    companion object {
        const val BASE_URL = "https://www.googleapis.com/books/v1/"
    }
}