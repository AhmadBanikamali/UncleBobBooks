package com.abcdandroid.presenter.allbooks

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abcdandroid.domain.model.DataSource
import com.abcdandroid.domain.model.GenericResponse
import com.abcdandroid.domain.model.UiBook
import com.abcdandroid.domain.use_case.BookUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllBooksViewModel @Inject constructor(private val bookUseCase: BookUseCase) : ViewModel() {

    var state by mutableStateOf(AllBooksState())

    fun getBooksFromRemote() {
        viewModelScope.launch {
            bookUseCase.getBooksFromSSOT().collect { response: GenericResponse<List<UiBook>> ->
                state = when (response) {
                    is GenericResponse.Loading -> state.copy(
                        isLoading = true,
                        data = listOf(),
                        hasError = null,
                        refreshed = false
                    )
                    is GenericResponse.Result -> when (response.dataSource) {
                        DataSource.Local -> state.copy(
                            isLoading = false,
                            data = response.result.getOrNull(),
                            hasError = null,
                            refreshed = false
                        )
                        DataSource.Remote -> if (response.result.isSuccess) state.copy(
                            isLoading = false,
                            data = response.result.getOrNull(),
                            hasError = null,
                            refreshed = false
                        ) else state.copy(
                            isLoading = false,
                            data = null,
                            hasError = response.result.exceptionOrNull()?.localizedMessage,
                            refreshed = true
                        )

                    }
                }
            }
        }
    }
}