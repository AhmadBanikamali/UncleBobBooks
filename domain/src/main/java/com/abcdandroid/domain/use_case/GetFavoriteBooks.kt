package com.abcdandroid.domain.use_case

import com.abcdandroid.domain.Repository
import com.abcdandroid.domain.model.UiBook
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteBooks @Inject constructor(private val repository: Repository) {
    operator fun invoke():Flow<List<UiBook>>{
        return repository.getFavoriteBooks()
    }
}