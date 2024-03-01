package com.example.imtihonfribase.presenter.screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.imtihonfribase.R
import com.example.imtihonfribase.data.ProductData
import com.example.imtihonfribase.databinding.ScreenProductDetailsBinding
import com.example.imtihonfribase.presenter.viewmodule.ProductDetailsViewModule
import com.example.imtihonfribase.presenter.viewmodule.impl.ProductDetailsViewModuleImpl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsScreen : Fragment(R.layout.screen_product_details) {
    private val binding by viewBinding(ScreenProductDetailsBinding::bind)
    private val viewModule: ProductDetailsViewModule by viewModels<ProductDetailsViewModuleImpl>()
    private val navArgs: ProductDetailsScreenArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val data = navArgs.productData
        setProductData(data)

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setProductData(data: ProductData) {
        binding.productName.text = data.typeName
        binding.productSum.text = data.price + " so'm"
        val sumString = data.price.replace(" ", "")
        val sum = sumString.toInt() / 12 * 1.0
        binding.textSumOy.text = "$sum so'm/oyiga"

        Glide.with(binding.root)
            .load(data.image)
            .into(binding.productImage)

        binding.productSolid.text = "100 ta buyurtma"
    }
}