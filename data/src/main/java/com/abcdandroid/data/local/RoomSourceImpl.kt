package com.abcdandroid.data.local

import com.abcdandroid.data.local.model.DaoBook
import com.abcdandroid.data.local.room.Dao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoomSourceImpl @Inject constructor(private val dao: Dao) : LocalSource {

    override suspend fun insertBooks(list: List<DaoBook>) = dao.insertBooks(list)

    override fun getAllBooks(): Flow<List<DaoBook>> = dao.getAllBooks()

    override suspend fun addToFavorites(id: String) = dao.addToFavorites(id)

    override suspend fun removeFromFavorites(id: String) = dao.removeFromFavorites(id)

    override fun getFavoriteBooks(): Flow<List<DaoBook>> = dao.getFavoriteBooks()
}