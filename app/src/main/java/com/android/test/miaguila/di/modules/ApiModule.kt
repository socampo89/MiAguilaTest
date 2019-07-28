package com.android.test.miaguila.di.modules

import com.android.test.miaguila.networking.api.GoogleMapsApi
import com.android.test.miaguila.networking.api.MiAguilaApi
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApiModule {

    companion object {
        const val CONNECT_TIMEOUT: Long = 10
        const val READ_TIME_OUT: Long = 30
    }

    @Provides
    fun providesOkHttpClient(): OkHttpClient.Builder {
        val httpClient = OkHttpClient.Builder()
        httpClient.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        httpClient.readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
        return httpClient
    }

    @Singleton
    @Provides
    fun providesApiService(httpClient: OkHttpClient.Builder): MiAguilaApi {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(MiAguilaApi.BASE_URL)
            .client(httpClient.build())
            .build()
        return retrofit.create(MiAguilaApi::class.java)
    }

    @Singleton
    @Provides
    fun providesGoogleMapsApiService(httpClient: OkHttpClient.Builder): GoogleMapsApi {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(GoogleMapsApi.BASE_URL)
            .client(httpClient.build())
            .build()
        return retrofit.create(GoogleMapsApi::class.java)
    }
}