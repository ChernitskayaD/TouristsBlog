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

package com.example.touristsblog.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.navigation.NavType
import com.example.touristsblog.navigation.routing.ArgumentOptions
import com.example.touristsblog.navigation.routing.ScreenRoute

object Routes {

    object Greetings : ScreenRoute(
        routeDefinition = Definition("greetings")
    )

    object Login : ScreenRoute(
        routeDefinition = Definition("login")
    )

    object SignUp : ScreenRoute(
        routeDefinition = Definition("SignUp")
    )

    object CreatePost : ScreenRoute(
        routeDefinition = Definition(
            "CreatePost",
            argumentKeys = listOf(
                "postId" to { type = NavType.StringType; optional = true }
            )
        ),
    )

    object PostList : ScreenRoute(
        routeDefinition = Definition("PostList"),
        icon = Icons.Default.Create,
        title = "Мои посты",
    )

    object Feed : ScreenRoute(
        routeDefinition = Definition("Feed"),
        icon = Icons.Default.List,
        title = "Лента",
    )

    object Profile : ScreenRoute(
        routeDefinition = Definition("Profile"),
        icon = Icons.Default.AccountCircle,
        title = "Профиль",
    )

    object ViewPost : ScreenRoute(
        routeDefinition = Definition(
            "ViewPost",
            argumentKeys = listOf(
                "postId" to { type = NavType.StringType; optional = false }
            )
        )
    )
}