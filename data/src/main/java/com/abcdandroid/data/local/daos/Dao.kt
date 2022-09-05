package com.abcdandroid.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.abcdandroid.data.local.model.DaoBook
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBooks(list: List<DaoBook>)

    @Query("select * from DaoBook")
    fun getAllBooks(): Flow<List<DaoBook>>


    @Query("update DaoBook set isFavorite = 1 where id = :id")
    suspend fun addToFavorites(id: String)


    @Query("update DaoBook set isFavorite = 0 where id = :id")
    suspend fun removeFromFavorites(id: String)


    @Query("select * from DaoBook where isFavorite = 1")
    fun getFavoriteBooks(): Flow<List<DaoBook>>

}