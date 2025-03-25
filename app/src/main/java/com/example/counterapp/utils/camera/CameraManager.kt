package com.example.counterapp.utils.camera

import android.content.Context
import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.example.counterapp.utils.ScanCode

class CameraManager(
    private val scanCode: ScanCode
) {
    private var cameraProvider: ProcessCameraProvider? = null

    fun startCamera(
        context: Context,
        lifecycleOwner: LifecycleOwner,
        onQRCodeDetected: (String?) -> Unit
    ) {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(context)

        cameraProviderFuture.addListener({
            cameraProvider = cameraProviderFuture.get()

            val imageAnalyzer = ImageAnalysis.Builder()
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build()
                .also { analysis ->
                    analysis.setAnalyzer(ContextCompat.getMainExecutor(context)) { imageProxy ->
                        val qrCodeResult = scanCode.decodeQRCode(imageProxy)
                        onQRCodeDetected(qrCodeResult)
                    }
                }

            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                cameraProvider?.unbindAll()
                cameraProvider?.bindToLifecycle(
                    lifecycleOwner,
                    cameraSelector,
                    imageAnalyzer
                )
            } catch (e: Exception) {
                Log.e("CameraManager", "Use case binding failed", e)
            }
        }, ContextCompat.getMainExecutor(context))
    }

    fun stopCamera() {
        cameraProvider?.unbindAll()
    }
}
