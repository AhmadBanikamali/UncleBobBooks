package com.abcdandroid.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DaoBook(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val bookId: String,
    val name: String?,
    val coverImage: String?,
    val author: List<String?>?
)