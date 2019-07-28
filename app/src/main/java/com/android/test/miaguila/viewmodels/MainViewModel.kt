package com.android.test.miaguila.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.test.miaguila.models.RouteModel
import com.android.test.miaguila.networking.data.RouteData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel : ViewModel() {

    private var routeModel : RouteModel = RouteModel()

    private val disposables = CompositeDisposable()

    val liveDataRoute = MutableLiveData<RouteData.Route>()

    fun getRoute(){
        disposables.add(routeModel.getRoute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                liveDataRoute.value = it
            }, Throwable::printStackTrace)
        )
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}