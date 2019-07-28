package com.android.test.miaguila.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.test.miaguila.models.GoogleDirectionsModel
import com.android.test.miaguila.models.RouteModel
import com.android.test.miaguila.networking.data.RouteData
import com.google.android.gms.maps.model.LatLng
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel : ViewModel() {

    private var routeModel : RouteModel = RouteModel()
    private var googleDirectionsModel = GoogleDirectionsModel()

    private val disposables = CompositeDisposable()

    val liveDataRoute = MutableLiveData<RouteData.Route>()
    val liveDataPolyline = MutableLiveData<MutableList<LatLng>>()

    fun getRoute(){
        disposables.add(routeModel.getRoute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                liveDataRoute.value = it
            }, Throwable::printStackTrace)
        )
    }

    fun getPolylines(origin : String, destination: String){
        disposables.add(googleDirectionsModel.getPolylines(origin, destination)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                 liveDataPolyline.value = it
            },Throwable::printStackTrace))
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}