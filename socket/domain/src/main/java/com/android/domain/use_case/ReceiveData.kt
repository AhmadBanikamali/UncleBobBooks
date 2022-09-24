package com.android.domain.use_case

import com.android.domain.Repository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import javax.inject.Inject

class ReceiveData @Inject constructor (private val repository: Repository) {
    operator fun invoke(): Channel<String> = repository.receiveDate()
}