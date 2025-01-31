package com.samapp.getswipeinternshipassigment.domain.use_case

import android.util.Log
import com.samapp.getswipeinternshipassigment.common.Resource
import com.samapp.getswipeinternshipassigment.data.dto.productAddedResponse
import com.samapp.getswipeinternshipassigment.domain.model.productItem
import com.samapp.getswipeinternshipassigment.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException

class AddProductUseCase(
    private val repository: AppRepository
) {
    suspend operator fun invoke(
        productItem: productItem
    ): Resource<productAddedResponse> {
        return try {
            val response = repository.addProduct(
                productName = productItem.product_name,
                productType = productItem.product_type,
                price = productItem.price  ,
                tax = productItem.tax,
                files = productItem.files
            )
            Log.d("API_REQUEST", "Sending request: $response")
            if (response.success) {
                Resource.Success(response)
            } else {
                Resource.Error("Failed to add product")
            }
        } catch (e: HttpException) {
            Log.d("API_REQUEST", "error 1: $e")
            Resource.Error(e.localizedMessage ?: "An unexpected error occurred")
        } catch (e: IOException) {
            Log.d("API_REQUEST", "error 2: $e")
            Resource.Error("Couldn't reach server. Check your internet connection.")
        }
    }
}

