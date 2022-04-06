package com.sumit.employedetails.database

import androidx.room.*
import com.sumit.employedetails.models.ResponseData
import com.sumit.employedetails.models.Sales

@Dao
interface SalesDao {
    @Insert
    suspend fun addSales(vararg sales: ResponseData)

    @Query("SELECT * FROM responsedata")
    suspend fun getAllSales(): ResponseData
}