package com.android.test.miaguila.di.modules

import com.android.test.miaguila.application.MiAguilaApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private  val applicationContext : MiAguilaApp) {

    @Provides
    @Singleton
    fun providesAppContext() : MiAguilaApp = applicationContext
}