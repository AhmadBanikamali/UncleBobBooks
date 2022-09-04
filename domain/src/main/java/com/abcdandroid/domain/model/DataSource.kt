package com.abcdandroid.domain.model

sealed class DataSource{
    object Local: DataSource()
    object Remote: DataSource()
}