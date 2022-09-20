package com.abcdandroid.domain.use_case

import javax.inject.Inject

data class BookUseCase @Inject constructor(
    val addBookToFavorites: AddBookToFavorite,
    val getBooksFromRemote: GetBooksFromRemote,
    val getBooksFromLocal: GetBooksFromLocal,
    val getFavoriteBooks: GetFavoriteBooks,
    val getBooksFromSSOT: GetBooksFromSSOT,
    val refreshDataBase: RefreshDataBase,
    val removeBookFromFavorites: RemoveBookToFavorite,
)
