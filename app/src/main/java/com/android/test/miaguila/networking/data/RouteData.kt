package com.android.test.miaguila.networking.data


import com.google.gson.annotations.SerializedName

data class RouteData(
    @SerializedName("route") val route: Route = Route()
) {
    data class Route(
        @SerializedName("end_address") val endAddress: String = "",
        @SerializedName("end_location") val endLocation: List<Double> = listOf(),
        @SerializedName("end_time") val endTime: String = "",
        @SerializedName("start_address") val startAddress: String = "",
        @SerializedName("start_location") val startLocation: List<Double> = listOf(),
        @SerializedName("start_time") val startTime: String = ""
    )
}