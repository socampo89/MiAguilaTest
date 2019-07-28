package com.android.test.miaguila.networking.api

import com.android.test.miaguila.networking.data.DirectionsGoogleData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleMapsApi {
    companion object{
        const val BASE_URL = "https://maps.googleapis.com/maps/api/"
    }

    @GET("directions/json")
    fun getDirections(@Query("key") key : String, @Query("origin") origin : String, @Query("destination") destination : String, @Query("mode") mode : String = "driving") : Observable<DirectionsGoogleData>
}