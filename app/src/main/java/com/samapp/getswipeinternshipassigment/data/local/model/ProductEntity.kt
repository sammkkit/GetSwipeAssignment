package com.samapp.getswipeinternshipassigment.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.samapp.getswipeinternshipassigment.data.remote.dto.productDtoItem
import com.samapp.getswipeinternshipassigment.domain.model.productItem

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val product_name: String,
    val product_type: String,
    val price: String,
    val tax: String,
    val files: String
)


fun ProductEntity.toProductDto() : productDtoItem {
    return productDtoItem(
        image = files,
        price = price.toDouble()?:0.0,
        product_name = product_name,
        product_type = product_type,
        tax = tax.toDouble()?:0.0
    )
}