package com.samapp.getswipeinternshipassigment.presentation.productAddition

import com.samapp.getswipeinternshipassigment.data.remote.dto.productAddedResponse

data class ProductAdditionState(
    val isLoading: Boolean = false,
    val productAddedResponse: productAddedResponse? = null,
    val error: String? = null
)
