package com.example.counterapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.counterapp.ui.components.Splash
import com.example.counterapp.viewmodel.SplashViewModel

@Composable
fun SplashScreen(navController: NavController) {
    val splashViewModel = remember { SplashViewModel() }
    val isSplashScreenShowing = splashViewModel.showSplashScreen.collectAsState()

    if (!isSplashScreenShowing.value) {
        navController.navigate("home") {
//            popUpTo("splash") { inclusive = true }
        }
    }

    Splash()
}

