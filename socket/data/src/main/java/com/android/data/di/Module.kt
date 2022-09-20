package com.android.data.di

import android.app.Application
import com.android.data.RepositoryImpl
import com.android.data.remote.rest.RestService
import com.android.data.remote.rest.RetrofitRestService
import com.android.data.remote.rest.RetrofitRestServiceImpl
import com.android.data.remote.ws.ScarletSocketService
import com.android.data.remote.ws.ScarletSocketServiceImpl
import com.android.data.remote.ws.SocketService
import com.android.domain.Repository
import com.tinder.scarlet.Lifecycle
import com.tinder.scarlet.MessageAdapter
import com.tinder.scarlet.Scarlet
import com.tinder.scarlet.StreamAdapter
import com.tinder.scarlet.lifecycle.android.AndroidLifecycle
import com.tinder.scarlet.messageadapter.gson.GsonMessageAdapter
import com.tinder.scarlet.retry.BackoffStrategy
import com.tinder.scarlet.retry.LinearBackoffStrategy
import com.tinder.scarlet.websocket.okhttp.newWebSocketFactory
import com.tinder.streamadapter.coroutines.CoroutinesStreamAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class Module {

    companion object {

        @Provides
        @Singleton
        fun provideScarlet(
            okHttpClient: OkHttpClient,
            streamFactory: StreamAdapter.Factory,
            backoffStrategy: BackoffStrategy,
        ): Scarlet = Scarlet.Builder()
            .webSocketFactory(okHttpClient.newWebSocketFactory("ws://192.168.27.2:22080"))
            .addStreamAdapterFactory(streamFactory)
            .backoffStrategy(backoffStrategy)
            .build()

        @Provides
        @Singleton
        fun provideRetrofitRestService(retrofitBuilder: Retrofit.Builder): RetrofitRestService =
            retrofitBuilder.baseUrl("https://dummy.restapiexample.com/api/v1/employee/").build()
                .create()


        @Provides
        @Singleton
        fun provideBackOffStrategy(): BackoffStrategy = LinearBackoffStrategy(10)


        @Provides
        @Singleton
        fun provideLifeCycle(application: Application): Lifecycle =
            AndroidLifecycle.ofApplicationForeground(application)


        @Provides
        @Singleton
        fun provideMessageAdapter(): MessageAdapter.Factory = GsonMessageAdapter.Factory()


        @Provides
        @Singleton
        fun provideStreamAdapter(): StreamAdapter.Factory = CoroutinesStreamAdapterFactory()


        @Provides
        @Singleton
        fun provideScarletSocketService(scarlet: Scarlet): ScarletSocketService =
            scarlet.create(ScarletSocketService::class.java)


    }


    @Binds
    @Singleton
    abstract fun bindRepository(repositoryImpl: RepositoryImpl): Repository

    @Binds
    @Singleton
    abstract fun bindRestService(retrofitRestServiceImpl: RetrofitRestServiceImpl): RestService

    @Binds
    @Singleton
    abstract fun bindSocketService(scarletSocketServiceImpl: ScarletSocketServiceImpl): SocketService


}