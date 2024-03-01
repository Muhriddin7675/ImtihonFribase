package com.example.imtihonfribase.domain

import com.example.imtihonfribase.data.ProductData
import kotlinx.coroutines.flow.Flow

interface AppRepository {
    fun getAllProduct():Flow<Result<List<ProductData>>>
}
