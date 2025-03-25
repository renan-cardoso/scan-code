package com.example.counterapp.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.counterapp.data.network.ConnectivityStatus
import com.example.counterapp.utils.enums.ScanStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel(
    private val context: Context
) : ViewModel() {
    private val _scanStatus = MutableStateFlow<ScanStatus>(ScanStatus.IDLE)
    val scanStatus: StateFlow<ScanStatus> = _scanStatus.asStateFlow()

    private val connectivityStatus = ConnectivityStatus(context)
    val internetStatus = connectivityStatus

    fun updateScanStatus(result: String?) {
        Log.d("TEST ->", "RESULT: $result")

        var scanStatusLocal = ScanStatus.IDLE

        if(result == "https://google.com") {
            scanStatusLocal = ScanStatus.SUCCESS
        }else if(result == "https://www.wikipedia.org") {
            scanStatusLocal = ScanStatus.ERROR
        }else
            scanStatusLocal = ScanStatus.IDLE

        _scanStatus.value = scanStatusLocal
    }
}