package com.abcdandroid.domain.use_case

import com.abcdandroid.domain.Repository
import com.abcdandroid.domain.model.UiBook
import com.abcdandroid.domain.model.GenericResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBooksFromRemote @Inject constructor(private val repository: Repository) {
    operator fun invoke(): Flow<GenericResponse<List<UiBook>>>{
        return repository.getBooksFromRemote()
    }
}