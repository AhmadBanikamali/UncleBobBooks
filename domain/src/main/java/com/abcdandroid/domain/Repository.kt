package com.abcdandroid.domain

import com.abcdandroid.domain.model.GenericResponse
import com.abcdandroid.domain.model.UiBook
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun getBooksFromRemote(): Flow<GenericResponse<List<UiBook>>>

    fun getBooksFromLocal(): Flow<List<UiBook>>

    fun getBooksFromSSOT(): Flow<GenericResponse<List<UiBook>>>

}