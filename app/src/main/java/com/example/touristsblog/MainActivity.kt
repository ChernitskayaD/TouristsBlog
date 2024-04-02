package com.example.touristsblog

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuDefaults.textFieldColors
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgs
import com.example.touristsblog.ui.screen.signup.SignUpScreen
import com.example.touristsblog.ui.screen.signup.SignUpViewModel
import com.example.touristsblog.ui.screen.login.LoginViewModel
import com.example.touristsblog.ui.theme.TouristsBlogTheme
import dagger.hilt.android.AndroidEntryPoint
import com.example.touristsblog.navigation.NavigationComponent
import com.example.touristsblog.navigation.NavigationControllerImpl
import com.example.touristsblog.navigation.composable
import com.example.touristsblog.navigation.Routes
import com.example.touristsblog.navigation.routing.ScreenRoute
import com.example.touristsblog.ui.screen.feed.FeedScreen
import com.example.touristsblog.ui.screen.feed.FeedViewModel
import com.example.touristsblog.ui.screen.myposts.createpost.CreatePostScreen
import com.example.touristsblog.ui.screen.myposts.createpost.CreatePostViewModel
import com.example.touristsblog.ui.screen.greetings.GreetingsScreen
import com.example.touristsblog.ui.screen.greetings.GreetingsViewModel
import com.example.touristsblog.ui.screen.login.LoginScreen
import com.example.touristsblog.ui.screen.myposts.postslist.PostListScreen
import com.example.touristsblog.ui.screen.myposts.postslist.PostListViewModel
import com.example.touristsblog.ui.screen.profile.ProfileScreen
import com.example.touristsblog.ui.screen.profile.ProfileViewModel
import com.example.touristsblog.ui.screen.viewpost.ViewPostScreen
import com.example.touristsblog.ui.screen.viewpost.ViewPostViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navHostState = rememberNavController()
            val controller = NavigationControllerImpl(navHostState)
            TouristsBlogTheme {
                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(controller.getNavController())
                    },
                    backgroundColor = MaterialTheme.colors.background,
                ) {
                    NavigationComponent(
                        startRoute = Routes.Greetings,
                        navigationController = controller
                    ) {
                        composable<GreetingsViewModel>(
                            route = Routes.Greetings,
                            navigationController = controller
                        ) { _, vm ->
                            GreetingsScreen(vm)
                        }
                        composable<LoginViewModel>(
                            route = Routes.Login,
                            navigationController = controller
                        ) { _, vm ->
                            LoginScreen(vm)
                        }

                        composable<SignUpViewModel>(
                            route = Routes.SignUp,
                            navigationController = controller
                        ) { _, vm ->
                            SignUpScreen(vm)
                        }

                        composable<CreatePostViewModel>(
                            route = Routes.CreatePost,
                            navigationController = controller
                        ) { _, vm ->
                            CreatePostScreen(vm)
                        }
                        composable<FeedViewModel>(
                            route = Routes.Feed,
                            navigationController = controller
                        ) { _, vm ->
                            FeedScreen(vm)
                        }
                        composable<ProfileViewModel>(
                            route = Routes.Profile,
                            navigationController = controller
                        ) { _, vm ->
                            ProfileScreen(vm)
                        }
                        composable<PostListViewModel>(
                            route = Routes.PostList,
                            navigationController = controller
                        ) { _, vm ->
                            PostListScreen(vm)
                        }
                        composable<ViewPostViewModel>(
                            route = Routes.ViewPost,
                            navigationController = controller,
                        ) { _, vm ->
                            ViewPostScreen(vm)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        Routes.PostList,
        Routes.Feed,
        Routes.Profile
    )
    val currentRoute by navController.currentBackStackEntryAsState()

    val needToShow = items.map { it.routeDefinition.getRoutePath() }.contains(currentRoute?.destination?.route)
    if (needToShow) {
        BottomNavigation {
            items.forEach { screen ->
                BottomNavigationItem(
                    icon = { screen.icon?.let { Icon(imageVector = it, contentDescription = screen.routeDefinition.toString()) } },
                    label = { screen.title?.let { Text(it) } },
                    selected = currentRoute?.destination?.route == screen.routeDefinition.toString(),
                    onClick = {
                        navController.navigate(screen.routeDefinition.getRoutePath())
                    }
                )
            }
        }
    }
}
