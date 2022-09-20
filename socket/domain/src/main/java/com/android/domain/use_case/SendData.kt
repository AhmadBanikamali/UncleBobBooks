package com.android.domain.use_case

import com.android.domain.Repository
import javax.inject.Inject

class SendData @Inject constructor(private val repository: Repository) {
    operator fun invoke(message: String) {
        repository.sendData(message)
    }
}