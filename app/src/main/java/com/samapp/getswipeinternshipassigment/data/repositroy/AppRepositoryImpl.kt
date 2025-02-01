package com.samapp.getswipeinternshipassigment.data.repositroy

import com.samapp.getswipeinternshipassigment.data.local.dao.ProductDao
import com.samapp.getswipeinternshipassigment.data.local.model.toProductDto
import com.samapp.getswipeinternshipassigment.data.remote.api.productApiService
import com.samapp.getswipeinternshipassigment.data.remote.dto.productAddedResponse
import com.samapp.getswipeinternshipassigment.data.remote.dto.productDtoItem
import com.samapp.getswipeinternshipassigment.data.remote.dto.toProductEntity
import com.samapp.getswipeinternshipassigment.data.remote.dto.toProductItem
import com.samapp.getswipeinternshipassigment.domain.model.productItem
import com.samapp.getswipeinternshipassigment.domain.repository.AppRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AppRepositoryImpl(
    private val apiService: productApiService,
    private val productDao: ProductDao
) : AppRepository {
    override suspend fun getProducts(): List<productDtoItem> {
        return try {
            val productsFromApi = apiService.getProducts()

            // Store the fetched data in Room for future use
            withContext(Dispatchers.IO) {
                productDao.insertProducts(productsFromApi.map { it.toProductEntity()})
            }

            productsFromApi
        } catch (exception: Exception) {
            val products =
                withContext(Dispatchers.IO) {
                    productDao.getAllProducts()
                }
            products.map { it.toProductDto() }
        }
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