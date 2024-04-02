package com.example.touristsblog.navigation.routing

interface NavigableRoute<R: ScreenRoute> {

    val screenRoute: R

    val path: String

}