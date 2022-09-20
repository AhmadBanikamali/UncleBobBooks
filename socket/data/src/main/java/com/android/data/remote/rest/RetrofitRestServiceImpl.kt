package com.android.data.remote.rest

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitRestServiceImpl @Inject constructor(private val retrofitRestService: RetrofitRestService) : RestService {
    override suspend fun getEmployee(id: Int) = retrofitRestService.getEmployee(id)
}