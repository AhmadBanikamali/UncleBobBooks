package com.abcdandroid.data.local

import com.abcdandroid.data.local.model.DaoBook
import kotlinx.coroutines.flow.Flow

interface LocalSource {
    suspend fun insertBooks(list: List<DaoBook>)
    fun getAllBooks(): Flow<List<DaoBook>>

    suspend fun addToFavorites(id: String)

    suspend fun removeFromFavorites(id: String)

    fun getFavoriteBooks(): Flow<List<DaoBook>>
}