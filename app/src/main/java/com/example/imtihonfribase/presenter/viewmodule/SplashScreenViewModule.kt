package com.example.imtihonfribase.presenter.viewmodule

import kotlinx.coroutines.flow.Flow

interface SplashScreenViewModule {
    val openAllProductScreen:Flow<Unit>
    fun openAllProductScreen()
}