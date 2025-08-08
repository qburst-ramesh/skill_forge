package com.qburst.bind.skillforge.quiz.utils

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.qburst.bind.skillforge.quiz.presentation.ui.landing.LandingScreen
import com.qburst.bind.skillforge.quiz.presentation.ui.login.LoginScreen
import com.qburst.bind.skillforge.quiz.presentation.ui.splash.SplashScreen
import io.github.aakira.napier.Napier

sealed class NavigationRoute(val name: String) {
    object Splash : NavigationRoute(name = "splash")
    object Login : NavigationRoute(name = "login")
    object Landing : NavigationRoute(name = "landing")
}

@Composable
fun Router() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavigationRoute.Splash.name) {
        composable(NavigationRoute.Splash.name) {
            SplashScreen(
                onNavigate = { token ->
                    Napier.d("Route Token $token")
                    token?.let { navController.toHome() } ?: navController.toLogin()
                }
            )
        }
        composable(NavigationRoute.Login.name) {
            LoginScreen(
                onLoginSuccess = {
                    navController.toHome()
                }
            )
        }
        composable(NavigationRoute.Landing.name) {
            LandingScreen()
        }
    }
}

fun NavController.toHome(
    popUpToRoute: NavigationRoute? = NavigationRoute.Landing,
    inclusiveRoute: Boolean = true
) {
    this.navigate(NavigationRoute.Landing.name) {
        popUpToRoute?.let {
            popUpTo(popUpToRoute.name) {
                inclusive = inclusiveRoute
            }
        }
        launchSingleTop = true
    }
}

fun NavController.toLogin(
    popUpToRoute: NavigationRoute? = NavigationRoute.Login,
    inclusiveRoute: Boolean = true
) {
    this.navigate(NavigationRoute.Login.name) {
        popUpToRoute?.let {
            popUpTo(popUpToRoute.name) {
                inclusive = inclusiveRoute
            }
        }
        launchSingleTop = true
    }
}
