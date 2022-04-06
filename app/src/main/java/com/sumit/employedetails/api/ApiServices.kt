package com.sumit.employedetails.api

import com.sumit.employedetails.models.Sales
import retrofit2.Response
import retrofit2.http.GET

interface ApiServices {

    @GET("286f38b4-c6c1-4348-aabc-6d396dcbc4de")
    suspend fun getSales(): Response<Sales>
}