package com.example.touristsblog

import androidx.lifecycle.ViewModel
import com.example.touristsblog.navigation.NavigationController
import com.example.touristsblog.navigation.routing.NavigableRoute
import com.example.touristsblog.navigation.routing.ScreenRoute

abstract class BaseViewModel : ViewModel() {

    private var mNavigationController: NavigationController? = null

    fun setNavigationController(navigationController: NavigationController) {
        mNavigationController = navigationController
    }

    fun navBack(){
        mNavigationController?.getNavController()?.navigateUp()
    }

    /**
     * Navigate to the [destinationRoute]
     * @param destinationRoute destination
     */
    suspend fun <T : ScreenRoute> navigateTo(
        destinationRoute: NavigableRoute<T>
    ) = mNavigationController?.navigateTo(
        destinationRoute = destinationRoute
    ) ?: IllegalStateException("NavigationController is not defined, impossible to navigate to ${destinationRoute.path}")

    /**
     * Navigate back to the [destinationRoute] with the previous route [parentRoute]
     * @param destinationRoute destination route
     * @param parentRoute previous routes
     */
    suspend fun <R : ScreenRoute, PR : ScreenRoute> navigateBackTo(
        destinationRoute: NavigableRoute<R>,
        parentRoute: NavigableRoute<PR>
    ) = mNavigationController?.navigateBackTo(
        destinationRoute = destinationRoute,
        parentRoute = parentRoute
    ) ?: IllegalStateException("NavigationController is not defined, impossible to navigate to ${destinationRoute.path}")
}