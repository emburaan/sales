package com.sumit.employedetails.api

import com.sumit.employedetails.models.Sales
import retrofit2.Response

interface ApiHelper {
    suspend fun getSales(): Response<Sales>
}
