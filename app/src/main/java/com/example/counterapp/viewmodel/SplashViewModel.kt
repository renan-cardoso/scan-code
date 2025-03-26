package com.example.counterapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SplashViewModel(): ViewModel() {
    private val _showSplashScreen = MutableStateFlow<Boolean>(true)
    val showSplashScreen: StateFlow<Boolean> = _showSplashScreen.asStateFlow()

    init {
        initializeApp()
    }

    private fun initializeApp() {
        viewModelScope.launch {
//            delay(4000)
            _showSplashScreen.value = false
        }
    }
}