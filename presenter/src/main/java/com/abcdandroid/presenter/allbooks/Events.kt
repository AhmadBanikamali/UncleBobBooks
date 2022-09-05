package com.abcdandroid.presenter.allbooks

import com.abcdandroid.domain.model.UiBook

sealed class Events {
    object OnRefreshDataClick : Events()
    data class OnToggleFavoriteClick(val uiBook: UiBook, val isSelected: Boolean) : Events()
}