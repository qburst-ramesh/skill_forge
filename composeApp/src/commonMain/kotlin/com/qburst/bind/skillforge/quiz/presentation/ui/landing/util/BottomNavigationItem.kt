package com.qburst.bind.skillforge.quiz.presentation.ui.landing.util

import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.icon_home
import kotlinproject.composeapp.generated.resources.icon_my_learning
import kotlinproject.composeapp.generated.resources.icon_profile
import kotlinproject.composeapp.generated.resources.icon_search
import org.jetbrains.compose.resources.DrawableResource

sealed class BottomNavigationScreen(
    val route: String,
    val title: String,
    val icon: DrawableResource
) {

    object Home : BottomNavigationScreen(
        route = "home_screen",
        title = "Home",
        icon = Res.drawable.icon_home
    )

    object Search : BottomNavigationScreen(
        route = "search_screen",
        title = "Search",
        icon = Res.drawable.icon_search
    )

    object MyLearning : BottomNavigationScreen(
        route = "my_learning_screen",
        title = "My Learning",
        icon = Res.drawable.icon_my_learning
    )

    object Profile : BottomNavigationScreen(
        route = "profile_screen",
        title = "Profile",
        icon = Res.drawable.icon_profile
    )
}

val navigationItems = listOf(
    BottomNavigationScreen.Home,
    BottomNavigationScreen.Search,
    BottomNavigationScreen.MyLearning,
    BottomNavigationScreen.Profile,
)
