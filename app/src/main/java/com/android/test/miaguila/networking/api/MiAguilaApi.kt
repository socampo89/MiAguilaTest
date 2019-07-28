package com.android.test.miaguila.networking.api

import com.android.test.miaguila.networking.data.RouteData
import io.reactivex.Observable
import retrofit2.http.GET

interface MiAguilaApi {
    companion object{
        const val BASE_URL = "https://s3.amazonaws.com/"
    }

    @GET("mobile.miaguila/test1.json")
    fun getRoute() : Observable<RouteData>
}