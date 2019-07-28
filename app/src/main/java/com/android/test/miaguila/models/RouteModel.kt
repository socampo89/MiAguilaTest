package com.android.test.miaguila.models

import com.android.test.miaguila.application.MiAguilaApp
import com.android.test.miaguila.networking.api.MiAguilaApi
import com.android.test.miaguila.networking.data.RouteData
import io.reactivex.Observable

class RouteModel {

    var miAguilaApi : MiAguilaApi = MiAguilaApp.appComponent.miAguilaApi()

    fun getRoute() : Observable<RouteData.Route> {
       return miAguilaApi.getRoute().map { it.route }
    }
}