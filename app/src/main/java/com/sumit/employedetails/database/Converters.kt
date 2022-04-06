package com.sumit.employedetails.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.annotations.JsonAdapter
import com.google.gson.reflect.TypeToken
import com.sumit.employedetails.models.*
import java.lang.reflect.Type
import javax.inject.Inject

class SalesCountryConverter {

    @TypeConverter
    fun fromString(value: String?): List<SalesCountry> {
        val listType: Type = object : TypeToken<List<SalesCountry>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(salesCountry: List<SalesCountry>): String {
        return Gson().toJson(salesCountry)
    }
}

class SalesZoneConverter {

    @TypeConverter
    fun fromString(value: String?): List<SalesZone> {
        val listType: Type = object : TypeToken<List<SalesZone>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(salesZone: List<SalesZone>): String {
        return Gson().toJson(salesZone)
    }
}

class SalesRegionConverter {

    @TypeConverter
    fun fromString(value: String?): List<SalesRegion> {
        val listType: Type = object : TypeToken<List<SalesRegion>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(salesRegion: List<SalesRegion>): String {
        return Gson().toJson(salesRegion)
    }
}

class SalesAreaConverter {

    @TypeConverter
    fun fromString(value: String?): List<SalesArea> {
        val listType: Type = object : TypeToken<List<SalesArea>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(salesArea: List<SalesArea>): String {
        return Gson().toJson(salesArea)
    }
}