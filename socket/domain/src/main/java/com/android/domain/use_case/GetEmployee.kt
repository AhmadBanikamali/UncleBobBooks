package com.android.domain.use_case

import com.android.domain.Repository
import javax.inject.Inject

class GetEmployee @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(id: Int) = repository.getEmployee(id)
}