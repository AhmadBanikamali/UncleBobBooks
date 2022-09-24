package com.android.data.di

import android.app.Application
import com.android.data.RepositoryImpl
import com.android.data.remote.rest.RestService
import com.android.data.remote.rest.RetrofitRestService
import com.android.data.remote.rest.RetrofitRestServiceImpl
import com.android.data.remote.ws.SocketService
import com.android.data.remote.ws.scarlet.ScarletSocketService
import com.android.data.remote.ws.scarlet.ScarletSocketServiceImpl
import com.android.data.remote.ws.socketio.SocketIOSocketServiceImpl
import com.android.data.remote.ws.websocket.WebSocketServiceImpl
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
import dagger.hilt.components.SingletonComponent
import io.socket.client.Socket
import kotlinx.coroutines.CoroutineScope
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocketListener
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Qualifier
import javax.inject.Singleton
import io.socket.client.IO as IO_Socket
import kotlinx.coroutines.Dispatchers.IO as IO_Coroutine

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


        @Singleton
        @Provides
        fun provideSocketIOService(): Socket = IO_Socket.socket("http://192.168.27.2:3000")

        @Provides
        @Singleton
        fun provideCoroutineScope():CoroutineScope = CoroutineScope(IO_Coroutine)


        @Provides
        @Singleton
        fun provideRequest():Request = Request.Builder().url("http://192.168.27.2:22080").build()

    }


    @Binds
    @Singleton
    abstract fun bindRepository(repositoryImpl: RepositoryImpl): Repository

    @Binds
    @Singleton
    abstract fun bindRestService(retrofitRestServiceImpl: RetrofitRestServiceImpl): RestService

    @Binds
    @Singleton
    @ScarletScope
    abstract fun bindScarletSocketService(scarletSocketServiceImpl: ScarletSocketServiceImpl): SocketService

    @Binds
    @Singleton
    @SocketIOScope
    abstract fun bindSocketIOSocketService(socketIOSocketServiceImpl: SocketIOSocketServiceImpl): SocketService

    @Binds
    @Singleton
    @WebSocketScope
    abstract fun bindWebSocketSocketService(webSocketServiceImpl: WebSocketServiceImpl): SocketService

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class SocketIOScope


    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class ScarletScope


    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class WebSocketScope
}