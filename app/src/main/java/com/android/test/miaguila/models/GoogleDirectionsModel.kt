package com.android.test.miaguila.models

import com.android.test.miaguila.R
import com.android.test.miaguila.application.MiAguilaApp
import com.android.test.miaguila.networking.api.GoogleMapsApi
import com.android.test.miaguila.networking.data.DirectionsGoogleData
import com.android.test.miaguila.utils.Utils
import com.google.android.gms.maps.model.LatLng
import io.reactivex.Observable

class GoogleDirectionsModel {

    var googleMapsApi : GoogleMapsApi = MiAguilaApp.appComponent.googleMapsApi()
    private val apiKey = MiAguilaApp.appComponent.appContext().getString(R.string.google_maps_key)

    fun getDirections(origin : String, destination : String, mode : String = "driving") : Observable<DirectionsGoogleData> {
       return googleMapsApi.getDirections(apiKey ,origin, destination, mode)
    }

    fun getPolylines(origin: String, destination: String, mode: String = "driving") : Observable<MutableList<LatLng>>{
        return getDirections(origin, destination, mode).map {
            val latLngList = mutableListOf<LatLng>()
            it.routes.firstOrNull()?.let { route ->
                val decoded = Utils.decodePoly(route.overviewPolyline.points)
                latLngList.addAll(decoded)
            }
            latLngList
        }

    }
}