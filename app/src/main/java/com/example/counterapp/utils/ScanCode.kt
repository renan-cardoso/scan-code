package com.example.counterapp.utils

import com.google.zxing.BinaryBitmap
import com.google.zxing.MultiFormatReader
import com.google.zxing.NotFoundException
import com.google.zxing.common.HybridBinarizer
import androidx.camera.core.ImageProxy
import com.google.zxing.PlanarYUVLuminanceSource

class ScanCode {
    fun decodeQRCode(imageProxy: ImageProxy): String? {
        val buffer = imageProxy.planes[0].buffer
        val bytes = ByteArray(buffer.capacity())
        buffer.get(bytes)

        val source = PlanarYUVLuminanceSource(
            bytes,
            imageProxy.width,
            imageProxy.height,
            0, 0,
            imageProxy.width, imageProxy.height,
            false
        )
        val binaryBitmap = BinaryBitmap(HybridBinarizer(source))
        val reader = MultiFormatReader()

        return try {
            val result = reader.decode(binaryBitmap)
            result.text
        } catch (e: NotFoundException) {
            null
        } finally {
            imageProxy.close()
        }
    }
}
