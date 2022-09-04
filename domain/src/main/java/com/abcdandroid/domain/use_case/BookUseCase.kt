package com.abcdandroid.domain.use_case

import javax.inject.Inject

data class BookUseCase @Inject constructor(
    val addBookToFavorite: AddBookToFavorite,
    val getBooksFromRemote: GetBooksFromRemote,
    val getFavoriteBooks: GetFavoriteBooks,
    val getBooksFromSSOT: GetBooksFromSSOT,
    val getBooksFromLocal: GetBooksFromLocal,
)
