package com.abcdandroid.data.remote

import com.abcdandroid.data.remote.model.BooksResponse

interface RemoteSource {
    suspend fun getBooks(author: String): BooksResponse
}