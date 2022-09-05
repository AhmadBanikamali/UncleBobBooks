package com.abcdandroid.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DaoBook(
    @PrimaryKey
    val id: String,
    val name: String?,
    val coverImage: String?,
    val author: List<String?>?,
    val isFavorite:Boolean =false
)