package com.sumit.employedetails.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class Sales(
    @SerializedName("ResponseStatus")
    val responseStatus: Int,
    @SerializedName("ResponseData")
    val responseData: ResponseData,
)


@Entity
data class ResponseData(
    @PrimaryKey(autoGenerate = true) var id: Int = -1,
    @SerializedName("sales_country")
    val salesCountry: List<SalesCountry>,
    @SerializedName("sales_zone")
    val salesZone: List<SalesZone>,
    @SerializedName("sales_region")
    val salesRegion: List<SalesRegion>,
    @SerializedName("sales_area")
    val saleArea: List<SalesArea>
)

data class SalesArea(
    @SerializedName("area")
    val area: String
)

data class SalesRegion(
    @SerializedName("region")
    val region: String,
)

data class SalesZone(
    @SerializedName("territory")
    val territory: String
)

data class SalesCountry(
    @SerializedName("country")
    val country: String
)
