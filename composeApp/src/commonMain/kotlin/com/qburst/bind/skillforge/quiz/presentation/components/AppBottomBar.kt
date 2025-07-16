package com.qburst.bind.skillforge.quiz.presentation.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.qburst.bind.skillforge.quiz.presentation.theme.PrimaryColor
import com.qburst.bind.skillforge.quiz.presentation.ui.landing.util.BottomNavigationScreen
import com.qburst.bind.skillforge.quiz.presentation.ui.landing.util.navigationItems
import org.jetbrains.compose.resources.painterResource

@Composable
fun AppBottomBar(
    currentRoute: BottomNavigationScreen,
    onItemSelected: (BottomNavigationScreen) -> Unit
) {
    NavigationBar(
        containerColor = PrimaryColor,
        contentColor = Color.White
    ) {
        navigationItems.forEach { screen ->
            NavigationBarItem(
                selected = currentRoute == screen,
                onClick = { onItemSelected(screen) },
                icon = {
                    Icon(
                        painter = painterResource(screen.icon),
                        contentDescription = screen.title,
                        modifier = Modifier.width(24.dp).height(24.dp)
                    )
                },
                label = {
                    Text(text = screen.title)
                },
                colors = NavigationBarItemColors(
                    selectedIconColor = Color.White,
                    selectedTextColor = Color.White,
                    selectedIndicatorColor = Color.Gray,
                    unselectedIconColor = Color.LightGray,
                    unselectedTextColor = Color.LightGray,
                    disabledIconColor = Color.DarkGray,
                    disabledTextColor = Color.DarkGray
                )
            )
        }
    }
}