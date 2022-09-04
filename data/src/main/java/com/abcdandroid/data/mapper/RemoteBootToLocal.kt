package com.abcdandroid.data.mapper

import com.abcdandroid.data.local.model.DaoBook
import com.abcdandroid.data.remote.model.DtoBook
import com.abcdandroid.domain.model.UiBook


fun DtoBook.toUiBook() = UiBook(
    id = id,
    name = volumeInfo?.title,
    coverImage = volumeInfo?.imageLinks?.thumbnail,
    author = volumeInfo?.authors
)

fun DtoBook.toDaoBook() = DaoBook(
    bookId = id.orEmpty(),
    name = volumeInfo?.title,
    coverImage = volumeInfo?.imageLinks?.thumbnail,
    author = volumeInfo?.authors
)

fun DaoBook.toUiBook() = UiBook(
    id = bookId,
    name = name,
    coverImage = coverImage,
    author = author
)

fun List<DaoBook>.daoToUiListBook(): MutableList<UiBook> {
    val uiBookList = mutableListOf<UiBook>()
    forEach {
        uiBookList.add(it.toUiBook())
    }
    return uiBookList
}

fun List<DtoBook>.dtoListToDaoList(): List<DaoBook> {
    return map { it.toDaoBook() }
}