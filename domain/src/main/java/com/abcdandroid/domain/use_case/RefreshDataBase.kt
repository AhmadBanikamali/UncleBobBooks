package com.abcdandroid.domain.use_case

import com.abcdandroid.domain.Repository
import javax.inject.Inject

class RefreshDataBase @Inject constructor(private val repository: Repository) {
    operator fun invoke()  = repository.refreshDataBase()
}