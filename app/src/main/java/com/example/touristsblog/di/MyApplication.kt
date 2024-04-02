package com.example.touristsblog.di

import com.example.touristsblog.BaseApplication
import com.example.touristsblog.BuildConfig
import com.yandex.mapkit.MapKitFactory
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class MyApplication: BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        MapKitFactory.setApiKey(BuildConfig.YandexSdkApiKey)
    }
}