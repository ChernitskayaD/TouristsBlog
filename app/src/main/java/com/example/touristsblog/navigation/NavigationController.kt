package com.example.touristsblog.navigation

import androidx.navigation.NavHostController
import com.example.touristsblog.navigation.routing.NavigableRoute
import com.example.touristsblog.navigation.routing.ScreenRoute

interface NavigationController {

    /**
     * Return the [NavHostController] bind to the NavigationController
     */
    fun getNavController(): NavHostController

    /**
     * Navigate to the [destinationRoute]
     * @param destinationRoute destination
     */
    suspend fun <T : ScreenRoute> navigateTo(
        destinationRoute: NavigableRoute<T>
    )

    /**
     * Navigate back to the [destinationRoute] with the previous route [parentRoute]
     * @param destinationRoute destination route
     * @param parentRoute previous routes
     */
    suspend fun <R : ScreenRoute, PR : ScreenRoute> navigateBackTo(
        destinationRoute: NavigableRoute<R>,
        parentRoute: NavigableRoute<PR>
    )

}