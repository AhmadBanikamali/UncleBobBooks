package com.abcdandroid.data.remote

import com.abcdandroid.data.remote.model.BooksResponse
import com.abcdandroid.data.remote.retrofit.Api
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitSourceImpl @Inject constructor(private val api: Api) : RemoteSource {
    override suspend fun getBooks(author: String): BooksResponse {
        return api.getBooks(author)
    }
}