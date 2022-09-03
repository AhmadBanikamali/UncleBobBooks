package com.abcdandroid.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BookLocal(
    @PrimaryKey
    val id: Int = 0,
    val name: String,


    )
