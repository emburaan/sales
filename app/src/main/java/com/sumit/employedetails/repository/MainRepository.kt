package com.sumit.employedetails.repository

import com.sumit.employedetails.api.ApiHelper
import com.sumit.employedetails.database.SalesDao
import com.sumit.employedetails.models.Sales
import com.sumit.employedetails.utils.isInternetAvailable
import retrofit2.Response
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiHelper: ApiHelper,
    private val salesDao: SalesDao
) {
    suspend fun getSales(): Response<Sales> {
        return if (isInternetAvailable()) {
            apiHelper.getSales().body()?.responseData?.let { salesDao.addSales(it) }
            apiHelper.getSales()
        } else {
            Response.success(Sales(200, salesDao.getAllSales()))
        }

    }
}