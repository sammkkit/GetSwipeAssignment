package com.samapp.getswipeinternshipassigment.data.dto

import com.samapp.getswipeinternshipassigment.domain.model.productItem

data class productAddedResponse(
    val message: String,
    val product_details: productItem,
    val product_id: Int,
    val success: Boolean
)