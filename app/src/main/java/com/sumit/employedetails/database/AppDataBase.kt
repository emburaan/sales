package com.sumit.employedetails.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sumit.employedetails.models.ResponseData
import com.sumit.employedetails.models.SalesArea

@Database(entities = [ResponseData::class], version = 1)

@TypeConverters(
    SalesCountryConverter::class,
    SalesZoneConverter::class,
    SalesRegionConverter::class,
    SalesAreaConverter::class
)

abstract class AppDataBase : RoomDatabase() {
    abstract fun salesDao(): SalesDao
}