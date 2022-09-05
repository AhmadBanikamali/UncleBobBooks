package com.abcdandroid.presenter.allbooks

import com.abcdandroid.domain.model.UiBook

data class AllBooksState(
    val isLoading: Boolean = false,
    val data: List<UiBook>? = mutableListOf(),
    val favorites: List<UiBook>? = mutableListOf(),
    val error: String? = null,
    val refreshed:Boolean = false
)