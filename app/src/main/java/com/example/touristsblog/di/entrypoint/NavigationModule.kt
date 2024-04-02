package com.example.touristsblog.di.entrypoint

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import com.example.touristsblog.di.component.ComposableComponent

@InstallIn(ComposableComponent::class)
@EntryPoint
interface ComposableEntryPoint {

}