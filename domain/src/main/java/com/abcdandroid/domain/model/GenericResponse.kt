package com.abcdandroid.domain.model

sealed class GenericResponse<T> {
    class Loading<T> : GenericResponse<T>()
    data class Result<T>(val dataSource: DataSource,val result: kotlin.Result<T?>) : GenericResponse<T>()
}

