package com.abcdandroid.presenter.allbooks

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abcdandroid.domain.model.DataSource.Local
import com.abcdandroid.domain.model.DataSource.Remote
import com.abcdandroid.domain.model.GenericResponse
import com.abcdandroid.domain.use_case.BookUseCase
import com.abcdandroid.presenter.allbooks.Events.OnRefreshDataClick
import com.abcdandroid.presenter.allbooks.Events.OnToggleFavoriteClick
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllBooksViewModel @Inject constructor(private val bookUseCase: BookUseCase) : ViewModel() {

    var state by mutableStateOf(AllBooksState())

    fun onEvent(event: Events) {
        when (event) {
            OnRefreshDataClick -> refreshData()
            is OnToggleFavoriteClick -> {
                viewModelScope.launch {
                    if (event.isSelected) bookUseCase.addBookToFavorites(event.uiBook)
                    else bookUseCase.removeBookFromFavorites(event.uiBook)
                }
            }
        }
    }

    private fun refreshData() {
        viewModelScope.launch {
            bookUseCase.refreshDataBase().collect { response ->
                state = when (response) {
                    is GenericResponse.Loading ->
                        state.copy(
                            isLoading = true,
                            error = null,
                            refreshed = false
                        )
                    is GenericResponse.Result ->
                        if (response.result.isSuccess) {
                            state.copy(
                                isLoading = false,
                                error = null,
                                refreshed = true
                            )
                        } else state.copy(
                            isLoading = false,
                            error = response.result.exceptionOrNull()?.localizedMessage,
                            refreshed = true
                        )
                }
            }
        }
    }


    fun getFromSSOT() {
        viewModelScope.launch {
            bookUseCase.getBooksFromSSOT().collect { response ->
                state = when (response) {
                    is GenericResponse.Loading ->
                        state.copy(isLoading = true)
                    is GenericResponse.Result ->
                        when (response.dataSource) {
                            Local -> state.copy(data = response.result.getOrNull())
                            Remote ->
                                if (response.result.isSuccess) state.copy(
                                    isLoading = false,
                                    refreshed = true
                                ) else
                                    state.copy(
                                        error = response.result.exceptionOrNull()?.localizedMessage.orEmpty(),
                                        isLoading = false,
                                    )
                        }
                }

            }
        }
    }

    fun getFavorites() {
        viewModelScope.launch {
            bookUseCase.getFavoriteBooks().collect {
                state = state.copy(favorites = it)
            }
        }
    }
}