/*
 * Copyright 2021 Marco Cattaneo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.touristsblog.di

import com.example.touristsblog.BaseApplication
import com.example.touristsblog.BuildConfig
import com.yandex.mapkit.MapKitFactory
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class MyApplication: BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        //MapKitFactory.setApiKey(BuildConfig.YandexSdkApiKey)
        MapKitFactory.setApiKey("dd9ee03f-7844-466a-b36d-e4bc1bdc3924")
    }
}