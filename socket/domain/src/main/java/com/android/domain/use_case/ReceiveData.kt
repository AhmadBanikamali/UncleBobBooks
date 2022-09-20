package com.android.domain.use_case

import com.android.domain.Repository
import io.reactivex.Flowable
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

class ReceiveData @Inject constructor (private val repository: Repository) {
    operator fun invoke(): ReceiveChannel<String> = repository.receiveDate()
}