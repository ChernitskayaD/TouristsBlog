package com.example.touristsblog.di.component

import dagger.hilt.DefineComponent
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import com.example.touristsblog.di.scope.ComposableScope

@ComposableScope
@DefineComponent(parent = ActivityComponent::class)
interface ComposableComponent

@DefineComponent.Builder
interface ComposableComponentBuilder {
    fun build(): ComposableComponent
}

@EntryPoint
@InstallIn(ActivityComponent::class)
interface ComposableComponentBuilderEntryPoint {
    val composableBuilder: ComposableComponentBuilder
}