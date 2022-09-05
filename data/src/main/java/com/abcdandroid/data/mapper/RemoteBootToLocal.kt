package com.abcdandroid.data.mapper

import com.abcdandroid.data.local.model.DaoBook
import com.abcdandroid.data.remote.model.DtoBook
import com.abcdandroid.domain.model.UiBook


fun DtoBook.daoToUiBook() = UiBook(
    id = id,
    name = volumeInfo?.title,
    coverImage = volumeInfo?.imageLinks?.thumbnail,
    author = volumeInfo?.authors,
    isFavorite = false
)

fun DtoBook.dtoToDaoBook() = DaoBook(
    id = id.orEmpty(),
    name = volumeInfo?.title,
    coverImage = volumeInfo?.imageLinks?.thumbnail,
    author = volumeInfo?.authors
)

fun DaoBook.daoToUiBook() = UiBook(
    id = id,
    name = name,
    coverImage = coverImage,
    author = author,
    isFavorite = isFavorite
)

fun List<DaoBook>.daoListToUiList(): MutableList<UiBook> {
    val uiBookList = mutableListOf<UiBook>()
    forEach {
        uiBookList.add(it.daoToUiBook())
    }
    return uiBookList
}


fun List<DtoBook>.dtoListToDaoList(): List<DaoBook> =
    map { it.dtoToDaoBook() }


fun UiBook.toDaoBook() = DaoBook(
    id = id.orEmpty(),
    name = name,
    coverImage = coverImage,
    author = author,
    isFavorite = isFavorite

)