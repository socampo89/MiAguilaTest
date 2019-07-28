package com.android.test.miaguila.application

import android.app.Application
import com.android.test.miaguila.di.components.AppComponent
import com.android.test.miaguila.di.components.DaggerAppComponent
import com.android.test.miaguila.di.modules.AppModule

class MiAguilaApp : Application() {

    companion object{
        lateinit var appComponent : AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        setupDagger()
    }

    private fun setupDagger(){
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}