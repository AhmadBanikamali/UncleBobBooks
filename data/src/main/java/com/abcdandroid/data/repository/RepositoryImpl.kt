package com.abcdandroid.data.repository

import com.abcdandroid.data.local.daos.Dao
import com.abcdandroid.data.mapper.daoListToUiList
import com.abcdandroid.data.mapper.daoToUiBook
import com.abcdandroid.data.mapper.dtoListToDaoList
import com.abcdandroid.data.mapper.toDaoBook
import com.abcdandroid.data.remote.Api
import com.abcdandroid.domain.Repository
import com.abcdandroid.domain.model.DataSource.Local
import com.abcdandroid.domain.model.DataSource.Remote
import com.abcdandroid.domain.model.GenericResponse
import com.abcdandroid.domain.model.GenericResponse.Loading
import com.abcdandroid.domain.model.GenericResponse.Result
import com.abcdandroid.domain.model.UiBook
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.Result.Companion.failure
import kotlin.Result.Companion.success

@Singleton
class RepositoryImpl @Inject constructor(private val api: Api, private val dao: Dao) : Repository {

    private val authorName = "Robert%20C.%20Martin"

    override fun getBooksFromRemote(): Flow<GenericResponse<List<UiBook>>> =
        flow {
            emit(Loading())
            try {
                delay(5000)
                val booksResponse = api.getBooks(authorName)
                emit(
                    Result(
                        dataSource = Remote,
                        success(booksResponse.items.map { remoteBook -> remoteBook.daoToUiBook() })
                    )
                )
            } catch (e: Exception) {
                emit(Result(dataSource = Remote, failure(e)))
            }
        }


    override fun getBooksFromLocal(): Flow<List<UiBook>> =
        dao.getAllBooks().map { it.daoListToUiList() }


    override fun refreshDataBase(): Flow<GenericResponse<Any>> = flow {
        emit(Loading())
        delay(5000)
        try {
            api.getBooks(authorName).items.apply {
                dao.insertBooks(dtoListToDaoList())
                emit(Result(dataSource = Local, success(null)))
            }
        } catch (e: Exception) {
            emit(Result(dataSource = Remote, failure(e)))
        }

    }

    override fun getBooksFromSSOT(): Flow<GenericResponse<List<UiBook>>> {
        val daoFlow: Flow<Result<List<UiBook>>> =
            dao.getAllBooks()
                .map { Result(dataSource = Local, success(it.daoListToUiList())) }

        val dtoFlow: Flow<GenericResponse<List<UiBook>>> = flow {
            emit(Loading())
            delay(5000)
            try {
                dao.insertBooks(api.getBooks(authorName).items.dtoListToDaoList())
                emit(Result(Remote, success(null)))
            } catch (e: Exception) {
                emit(Result(Local, failure(e)))
            }
        }

        return merge(daoFlow, dtoFlow)
    }

    override suspend fun addBookToFavorites(book: UiBook) =
        dao.addToFavorites(book.toDaoBook().id)

    override suspend fun removeBookFromFavorites(book: UiBook) =
        dao.removeFromFavorites(book.toDaoBook().id)

    override fun getFavoriteBooks(): Flow<List<UiBook>> =
        dao.getFavoriteBooks().map { it.daoListToUiList() }


}