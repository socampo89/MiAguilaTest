package com.android.test.miaguila.networking.data


import com.google.gson.annotations.SerializedName

data class DirectionsGoogleData(
    @SerializedName("routes") val routes: List<Route> = listOf(),
    @SerializedName("status") val status: String = "",
    @SerializedName("error_message") val errorMessage : String = ""
) {
    data class Route(
        @SerializedName("overview_polyline") val overviewPolyline: OverviewPolyline = OverviewPolyline(),
        @SerializedName("summary") val summary: String = ""
    ) {
        data class OverviewPolyline(
            @SerializedName("points") val points: String = ""
        )
    }
}