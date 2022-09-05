package com.abcdandroid.domain.use_case

import com.abcdandroid.domain.Repository
import com.abcdandroid.domain.model.UiBook
import javax.inject.Inject

class AddBookToFavorite @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(book: UiBook) = repository.addBookToFavorites(book)
}