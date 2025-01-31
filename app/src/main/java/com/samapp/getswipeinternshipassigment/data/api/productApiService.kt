package com.samapp.getswipeinternshipassigment.data.api

import com.samapp.getswipeinternshipassigment.data.dto.productAddedResponse
import com.samapp.getswipeinternshipassigment.data.dto.productDtoItem
import com.samapp.getswipeinternshipassigment.domain.model.productItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface productApiService {
    @GET("get")
    suspend fun getProducts() : List<productDtoItem>

    @FormUrlEncoded
    @POST("add")
    suspend fun addProduct(
        @Field("product_name") productName: String,
        @Field("product_type") productType: String,
        @Field("price") price: String,
        @Field("tax") tax: String,
        @Field("files") files: String
    ) : productAddedResponse
}

