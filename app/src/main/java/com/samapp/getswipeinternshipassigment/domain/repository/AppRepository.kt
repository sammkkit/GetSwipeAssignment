package com.samapp.getswipeinternshipassigment.domain.repository

import com.samapp.getswipeinternshipassigment.data.remote.dto.productAddedResponse
import com.samapp.getswipeinternshipassigment.data.remote.dto.productDtoItem
import com.samapp.getswipeinternshipassigment.domain.model.productItem

interface AppRepository{
    suspend fun getProducts() : List<productDtoItem>
    suspend fun addProduct(
        productName: String,
        productType: String,
        price: String,
        tax: String,
        files: String
    ): productAddedResponse
}