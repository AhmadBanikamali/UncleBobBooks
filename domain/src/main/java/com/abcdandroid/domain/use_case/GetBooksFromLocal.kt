package com.abcdandroid.domain.use_case

import com.abcdandroid.domain.Repository
import com.abcdandroid.domain.model.UiBook
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBooksFromLocal @Inject constructor(private val repository: Repository) {

    operator fun invoke(): Flow<List<UiBook>> = repository.getBooksFromLocal()

}