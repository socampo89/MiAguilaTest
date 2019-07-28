package com.android.test.miaguila.di.components

import com.android.test.miaguila.application.MiAguilaApp
import com.android.test.miaguila.di.modules.ApiModule
import com.android.test.miaguila.di.modules.AppModule
import com.android.test.miaguila.networking.api.MiAguilaApi
import dagger.Component
import javax.inject.Singleton

@Component(modules = [(AppModule::class), (ApiModule::class)])
@Singleton
interface AppComponent {
    fun appContext() : MiAguilaApp
    fun miAguilaApi() : MiAguilaApi
}