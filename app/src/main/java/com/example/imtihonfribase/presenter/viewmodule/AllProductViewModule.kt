package com.example.imtihonfribase.presenter.viewmodule

import com.example.imtihonfribase.data.ProductData
import kotlinx.coroutines.flow.Flow

interface AllProductViewModule {
    val errorMessage:Flow<String>
    val loadAllProduct:Flow<List<ProductData>>
    val progressBar:Flow<Boolean>

    fun setProductList()
    fun setProductListByCurrentProduct(currentQuery: String)

}