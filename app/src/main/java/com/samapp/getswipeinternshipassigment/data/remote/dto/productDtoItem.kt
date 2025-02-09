package com.samapp.getswipeinternshipassigment.data.remote.dto

import com.samapp.getswipeinternshipassigment.data.local.model.ProductEntity
import com.samapp.getswipeinternshipassigment.domain.model.productItem

data class productDtoItem(
    val image: String,
    val price: Double,
    val product_name: String,
    val product_type: String,
    val tax: Double
)

fun productDtoItem.toProductItem() : productItem {
    return productItem(
        files = image,
        price = price.toString(),
        product_name = product_name,
        product_type = product_type,
        tax = tax.toString()
    )
}

fun productDtoItem.toProductEntity() : ProductEntity {
    return ProductEntity(
        files = image,
        price = price.toString(),
        product_name = product_name,
        product_type = product_type,
        tax = tax.toString()
    )
}