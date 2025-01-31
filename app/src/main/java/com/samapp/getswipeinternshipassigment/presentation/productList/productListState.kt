package com.samapp.getswipeinternshipassigment.presentation.productList

import com.samapp.getswipeinternshipassigment.domain.model.productItem

data class productListState(
    val isLoading: Boolean = false,
    val products: List<productItem> = emptyList(),
    val error: String? = null
)
