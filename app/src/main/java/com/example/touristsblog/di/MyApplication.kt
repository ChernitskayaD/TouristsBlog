package com.example.touristsblog.di

import com.example.touristsblog.BaseApplication
import com.example.touristsblog.BuildConfig
import com.yandex.mapkit.MapKitFactory
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class MyApplication: BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        MapKitFactory.setApiKey("dd9ee03f-7844-466a-b36d-e4bc1bdc3924")
        MapKitFactory.initialize(this)
    }
}