package com.samapp.getswipeinternshipassigment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.samapp.getswipeinternshipassigment.presentation.HomeScreen
import com.samapp.getswipeinternshipassigment.presentation.productList.ProductListScreen
import com.samapp.getswipeinternshipassigment.ui.theme.GetSwipeInternshipAssigmentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GetSwipeInternshipAssigmentTheme {
                HomeScreen()
            }
        }
    }
}
