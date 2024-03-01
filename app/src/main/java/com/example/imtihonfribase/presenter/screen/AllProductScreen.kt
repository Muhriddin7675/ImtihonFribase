package com.example.imtihonfribase.presenter.screen

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.imtihonfribase.R
import com.example.imtihonfribase.databinding.ScreenCategoryProductsBinding
import com.example.imtihonfribase.presenter.adapter.AllProductAdapter
import com.example.imtihonfribase.presenter.viewmodule.AllProductViewModule
import com.example.imtihonfribase.presenter.viewmodule.impl.AllProductViewModuleImpl
import com.example.imtihonfribase.util.myLog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class AllProductScreen : Fragment(R.layout.screen_category_products) {
    private val binding by viewBinding(ScreenCategoryProductsBinding::bind)
    private val viewModule: AllProductViewModule by viewModels<AllProductViewModuleImpl>()
    private val adapter by lazy { AllProductAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModule.setProductList()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.rvCategoryProduct.adapter = adapter
        binding.rvCategoryProduct.layoutManager = GridLayoutManager(requireContext(), 2)


        viewModule.loadAllProduct
            .onEach {
                adapter.submitList(it)
            }
            .launchIn(lifecycleScope)


        viewModule.errorMessage
            .onEach {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
            .launchIn(lifecycleScope)


        viewModule.progressBar.onEach {
            binding.progressBar.isVisible = it
            "$it progress".myLog()
        }.launchIn(lifecycleScope)


        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val currentQuery = newText
                if (currentQuery == null) viewModule.setProductList()
                else viewModule.setProductListByCurrentProduct(currentQuery)
                return true
            }
        })
        adapter.setOnClickProductItem {
            findNavController().navigate(
                AllProductScreenDirections.actionAllProductScreenToProductDetailsScreen(
                    it
                )
            )
        }
    }
}