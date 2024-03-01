package com.example.imtihonfribase.presenter.viewmodule.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imtihonfribase.data.ProductData
import com.example.imtihonfribase.domain.AppRepository
import com.example.imtihonfribase.presenter.viewmodule.AllProductViewModule
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllProductViewModuleImpl @Inject constructor(
    private val repository: AppRepository
) : ViewModel(), AllProductViewModule {
    override val errorMessage = MutableSharedFlow<String>()
    override val loadAllProduct = MutableSharedFlow<List<ProductData>>()
    override val progressBar = MutableStateFlow(false)
    private var list  = ArrayList<ProductData>()

    override fun setProductList() {
        progressBar.tryEmit(true)

        repository.getAllProduct()
            .onEach { result ->
                result.onSuccess {
                    progressBar.emit(false)
                    list = it as ArrayList<ProductData>
                    loadAllProduct.emit(it)
                }
                result.onFailure {
                    progressBar.emit(false)
                    errorMessage.emit(it.message.toString())
                }
            }
            .launchIn(viewModelScope)
    }

    override fun setProductListByCurrentProduct(currentProduct: String) {
        val ls = ArrayList<ProductData>()
        for (i in 0 until list.size) {
            if (list[i].typeName.toLowerCase().startsWith(currentProduct)) ls.add(list[i])
        }
        viewModelScope.launch {
            loadAllProduct.emit(ls)
        }
    }

}