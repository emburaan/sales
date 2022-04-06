package com.sumit.employedetails.api

import com.sumit.employedetails.models.Sales
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val apiServices: ApiServices
) : ApiHelper {
    override suspend fun getSales(): Response<Sales> = apiServices.getSales()
}