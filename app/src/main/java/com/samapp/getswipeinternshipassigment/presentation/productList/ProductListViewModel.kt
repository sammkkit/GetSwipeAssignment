package com.samapp.getswipeinternshipassigment.presentation.productList

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samapp.getswipeinternshipassigment.common.Resource
import com.samapp.getswipeinternshipassigment.domain.use_case.GetProductUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ProductListViewModel (
    private val getProductUseCase: GetProductUseCase
) : ViewModel(){
    private val _state = mutableStateOf(productListState())
    val state: State<productListState> = _state

    init {
        getProducts()
    }

    fun getProducts() {
        getProductUseCase().onEach { result->
            when(result){
                is Resource.Success -> {
                    _state.value = productListState(
                        products = result.data?: emptyList()
                    )
                }

                is Resource.Error -> {
                    _state.value = productListState(
                        error = result.message?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = productListState(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}