package com.example.counterapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.compose.rememberNavController
import com.example.counterapp.ui.navigation.AppNavigation
import com.example.counterapp.utils.camera.CameraUtils
import com.example.counterapp.utils.camera.CameraPermissionManager

class MainActivity : ComponentActivity() {
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Register launcher for camera permission
        requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                println("✅ Camera permission granted!")
                checkCameraAccessibility()
            } else {
                println("❌ Camera permission denied!")
            }
        }

        // Request camera permission
        if (!CameraPermissionManager.isPermissionGranted(this)) {
            CameraPermissionManager.requestCameraPermission(requestPermissionLauncher)
        } else {
            checkCameraAccessibility()
        }

        setContent {
            val navController = rememberNavController()
            AppNavigation(navController)
        }
    }

    private fun checkCameraAccessibility() {
        CameraUtils.isCameraAccessible(this) { accessible ->
            if (accessible) {
                println("✅ Camera is accessible")
            } else {
                println("❌ Camera is NOT accessible")
            }
        }
    }
}



