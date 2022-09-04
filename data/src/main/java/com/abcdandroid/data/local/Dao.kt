package com.abcdandroid.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.abcdandroid.data.local.model.DaoBook
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    @Insert
    suspend fun insertBooks(list: List<DaoBook>)

    @Query("select * from DaoBook")
    fun getAllBooks(): Flow<List<DaoBook>>
}