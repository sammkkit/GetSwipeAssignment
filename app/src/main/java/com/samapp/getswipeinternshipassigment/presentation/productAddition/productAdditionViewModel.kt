package com.samapp.getswipeinternshipassigment.presentation.productAddition

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import com.samapp.getswipeinternshipassigment.common.Resource
import com.samapp.getswipeinternshipassigment.domain.model.productItem
import com.samapp.getswipeinternshipassigment.domain.use_case.AddProductUseCase
import kotlinx.coroutines.launch

class productAdditionViewModel (
    private val addProductUseCase: AddProductUseCase
) : ViewModel(){
    private val _state = mutableStateOf(ProductAdditionState())
    val state: State<ProductAdditionState> = _state
    init {
        Log.d("ProductAdditionVM", "VM initiated")
        val testProduct = productItem(
            product_name = "Test Product",
            product_type = "Electronics",
            price = "500",
            tax = "18",
            files = "hey.com"
        )
        addProduct(testProduct)
    }
    fun addProduct(productItem: productItem) {
        Log.d("ProductAdditionVM", "Adding product: $productItem")
        _state.value = ProductAdditionState(isLoading = true)

        viewModelScope.launch {
            val result = addProductUseCase(productItem)
            Log.d("ProductAdditionVM", "result: $result")
            when (result) {
                is Resource.Success -> {
                    Log.d("ProductAdditionVM", "Product added successfully: ${result.data}")
                    _state.value = ProductAdditionState(
                        isLoading = false,
                        productAddedResponse = result.data,
                        error = null
                    )
                }
                is Resource.Error -> {
                    Log.e("ProductAdditionVM", "Error adding product: ${result.message}")
                    _state.value = ProductAdditionState(
                        isLoading = false,
                        productAddedResponse = null,
                        error = result.message
                    )
                }
                is Resource.Loading -> {
                    Log.d("ProductAdditionVM", "Loading...")
                    _state.value = ProductAdditionState(isLoading = true)
                }
            }
        }
    }
}