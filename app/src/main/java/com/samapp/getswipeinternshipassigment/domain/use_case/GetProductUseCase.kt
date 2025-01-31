package com.samapp.getswipeinternshipassigment.domain.use_case

import com.samapp.getswipeinternshipassigment.common.Resource
import com.samapp.getswipeinternshipassigment.data.dto.toProductItem
import com.samapp.getswipeinternshipassigment.domain.model.productItem
import com.samapp.getswipeinternshipassigment.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException

class GetProductUseCase(
    private val repository: AppRepository
) {
    operator fun invoke() : Flow<Resource<List<productItem>>> = flow{
        try{
            emit(Resource.Loading<List<productItem>>())
            val products = repository.getProducts()?.map { it.toProductItem() }
            emit(Resource.Success<List<productItem>>(products))
        }catch(e: HttpException) {
            emit(Resource.Error<List<productItem>>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<List<productItem>>("Couldn't reach server. Check your internet connection."))
        }
    }
}