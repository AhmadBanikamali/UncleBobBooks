package com.abcdandroid.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.abcdandroid.data.local.daos.Dao
import com.abcdandroid.data.local.model.DaoBook

@Database(entities = [DaoBook::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class BooksDataBase : RoomDatabase() {

    abstract fun dao(): Dao

    companion object {
        const val BOOKS_DATABASE = "BOOKS_DATABASE"
    }

}