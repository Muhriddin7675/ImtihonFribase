package com.example.imtihonfribase.presenter.viewmodule.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imtihonfribase.presenter.viewmodule.SplashScreenViewModule
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModuleImpl @Inject constructor() : ViewModel(), SplashScreenViewModule {
    override val openAllProductScreen = MutableSharedFlow<Unit>()

    override fun openAllProductScreen() {
        viewModelScope.launch {
            delay(1000)
            openAllProductScreen.emit(Unit)
        }
    }

}