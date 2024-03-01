package com.example.imtihonfribase.presenter.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.imtihonfribase.R
import com.example.imtihonfribase.databinding.ScreenSplashBinding
import com.example.imtihonfribase.presenter.viewmodule.SplashScreenViewModule
import com.example.imtihonfribase.presenter.viewmodule.impl.SplashScreenViewModuleImpl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SplashScreen:Fragment(R.layout.screen_splash) {
    private val viewModule:SplashScreenViewModule by viewModels<SplashScreenViewModuleImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

     viewModule.openAllProductScreen()
     viewModule.openAllProductScreen
         .onEach {
             findNavController().navigate(SplashScreenDirections.actionSplashScreenToAllProductScreen())
         }
         .launchIn(lifecycleScope)

    }

}