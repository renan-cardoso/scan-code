package com.example.counterapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavController
import com.example.counterapp.ui.components.ScreenContent
import com.example.counterapp.utils.ScanCode
import com.example.counterapp.utils.camera.CameraController
import com.example.counterapp.utils.camera.CameraManager
import com.example.counterapp.viewmodel.MainViewModel

@Composable
fun HomeScreen(navController: NavController) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    
    val mainViewModel = remember { MainViewModel(context) }

    val cameraController = remember {
        val cameraManager = CameraManager(ScanCode())
        CameraController(cameraManager, mainViewModel)
    }

    val internetStatus by mainViewModel.internetStatus.observeAsState(false)
    val scanStatus by mainViewModel.scanStatus.collectAsState()

    DisposableEffect(Unit) {
        cameraController.startCamera(context, lifecycleOwner)
        onDispose {
            cameraController.stopCamera()
        }
    }

    ScreenContent(
        internetStatus,
        scanStatus,
    )
}

