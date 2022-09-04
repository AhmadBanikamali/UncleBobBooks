package com.abcdandroid.data.repository

import com.abcdandroid.data.local.Dao
import com.abcdandroid.data.mapper.daoToUiListBook
import com.abcdandroid.data.mapper.dtoListToDaoList
import com.abcdandroid.data.mapper.toUiBook
import com.abcdandroid.data.remote.Api
import com.abcdandroid.domain.Repository
import com.abcdandroid.domain.model.DataSource.Local
import com.abcdandroid.domain.model.DataSource.Remote
import com.abcdandroid.domain.model.GenericResponse
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

    val authorName = "Robert%20C.%20Martin"

    override fun getBooksFromRemote(): Flow<GenericResponse<List<UiBook>>> =
        flow {
            emit(GenericResponse.Loading())
            try {
                val booksResponse = api.getBooks(authorName)
                emit(
                    GenericResponse.Result(
                        dataSource = Remote,
                        success(booksResponse.items.map { remoteBook -> remoteBook.toUiBook() })
                    )
                )
            } catch (e: Exception) {
                emit(GenericResponse.Result(dataSource = Remote, failure(e)))
            }
        }


    override fun getBooksFromLocal(): Flow<List<UiBook>> =
        dao.getAllBooks().map { it.daoToUiListBook() }


    override fun getBooksFromSSOT(): Flow<GenericResponse<List<UiBook>>> {
        val daoFlow: Flow<GenericResponse.Result<List<UiBook>>> =
            dao.getAllBooks()
                .map { GenericResponse.Result(dataSource = Local, success(it.daoToUiListBook())) }

        val dtoFlow: Flow<GenericResponse<List<UiBook>>> = flow {
            emit(GenericResponse.Loading())
            delay(2000)
            try {
                dao.insertBooks(api.getBooks("Robert%20C.%20Martin").items.dtoListToDaoList())
                emit(GenericResponse.Result(Remote, success(null)))
            } catch (e: Exception) {
                emit(GenericResponse.Result(Remote, failure(e)))
            }
        }

        return merge(daoFlow, dtoFlow)
    }


}