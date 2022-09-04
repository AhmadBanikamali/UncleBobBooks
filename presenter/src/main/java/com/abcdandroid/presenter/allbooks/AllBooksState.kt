package com.abcdandroid.presenter.allbooks

import com.abcdandroid.domain.model.UiBook

data class AllBooksState(
    val isLoading: Boolean = false,
    val data: List<UiBook>? = mutableListOf(),
    val hasError: String? = null,
    val refreshed:Boolean = false
)