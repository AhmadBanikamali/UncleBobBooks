package com.abcdandroid.data.di

import android.content.Context
import androidx.room.Room
import com.abcdandroid.data.local.LocalSource
import com.abcdandroid.data.local.RoomSourceImpl
import com.abcdandroid.data.local.room.BooksDataBase
import com.abcdandroid.data.remote.RemoteSource
import com.abcdandroid.data.remote.RetrofitSourceImpl
import com.abcdandroid.data.remote.retrofit.Api
import com.abcdandroid.data.repository.RepositoryImpl
import com.abcdandroid.domain.Repository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class Module {
    companion object {

        @Provides
        fun provideApi(retrofitBuilder: Retrofit.Builder): Api = retrofitBuilder
            .baseUrl(Api.BASE_URL)
            .build()
            .create()


        @Provides
        @Singleton
        fun provideDao(@ApplicationContext context: Context): BooksDataBase =
            Room.databaseBuilder(
                context,
                BooksDataBase::class.java,
                BooksDataBase.BOOKS_DATABASE
            ).fallbackToDestructiveMigration().build()


        @Provides
        @Singleton
        fun provideBooksDao(booksDataBase: BooksDataBase) = booksDataBase.dao()

    }

    @Binds
    abstract fun bindRepository(repositoryImpl: RepositoryImpl): Repository

    @Binds
    abstract fun bindLocalSource(localSource: RoomSourceImpl): LocalSource

    @Binds
    abstract fun bindRemoteSource(remoteSource: RetrofitSourceImpl): RemoteSource
}