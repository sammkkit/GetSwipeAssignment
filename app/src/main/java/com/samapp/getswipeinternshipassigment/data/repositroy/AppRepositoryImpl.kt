package com.samapp.getswipeinternshipassigment.data.repositroy

import com.samapp.getswipeinternshipassigment.data.api.productApiService
import com.samapp.getswipeinternshipassigment.data.dto.productAddedResponse
import com.samapp.getswipeinternshipassigment.data.dto.productDtoItem
import com.samapp.getswipeinternshipassigment.domain.model.productItem
import com.samapp.getswipeinternshipassigment.domain.repository.AppRepository

class AppRepositoryImpl(
    private val apiService: productApiService
) : AppRepository {
    override suspend fun getProducts(): List<productDtoItem> {
        return apiService.getProducts()
    }

    override suspend fun addProduct(
        productName: String,
        productType: String,
        price: String,
        tax: String,
        files: String
    ): productAddedResponse {
        return apiService.addProduct(
            productName = productName,
            productType = productType,
            price = price,
            tax = tax,
            files = files
        )
    }
}