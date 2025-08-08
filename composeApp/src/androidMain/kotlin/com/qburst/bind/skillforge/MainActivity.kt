package com.qburst.bind.skillforge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import org.koin.android.ext.koin.androidContext


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                lightScrim = ContextCompat.getColor(this, R.color.primary_color),
                darkScrim = ContextCompat.getColor(this, R.color.primary_color)
            ),
            navigationBarStyle = SystemBarStyle.auto(
                lightScrim = ContextCompat.getColor(this, R.color.primary_color),
                darkScrim = ContextCompat.getColor(this, R.color.primary_color)
            )
        )

        setContent {
            App {
                androidContext(this@MainActivity)
            }
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}