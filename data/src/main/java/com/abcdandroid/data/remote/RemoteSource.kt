package com.abcdandroid.data.remote

import com.abcdandroid.data.remote.model.BooksResponse
import retrofit2.http.Query

interface RemoteSource {
    suspend fun getBooks(author: String): BooksResponse
}