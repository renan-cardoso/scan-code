package com.example.counterapp.utils.camera

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.example.counterapp.viewmodel.MainViewModel

class CameraController(
    private val cameraManager: CameraManager,
    private val mainViewModel: MainViewModel
) {
    fun startCamera(context: Context, lifecycleOwner: LifecycleOwner) {
        cameraManager.startCamera(context, lifecycleOwner) { qrCodeResult ->
            mainViewModel.updateScanStatus(qrCodeResult)
        }
    }

    fun stopCamera() {
        cameraManager.stopCamera()
    }
}
