package com.samapp.getswipeinternshipassigment.presentation.productList.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.samapp.getswipeinternshipassigment.domain.model.productItem

@Composable
fun ProductItemComponent(
    modifier: Modifier = Modifier,
    productItem: productItem
) {
    Card(
        modifier = modifier
            .size(width = 160.dp, height = 215.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        ProductItemTopSection(
            modifier=Modifier,
            productItem = productItem,
            onFavoriteClick = {
                //TODO - onFavoriteClick
            }
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = productItem.product_name,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                ),
            )
            Text(
                text = productItem.product_type,
            )
            Text(
                text = "₹ ${productItem.price}",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                )
            )
        }
    }
}
@Preview(showBackground = false)
@Composable
fun ProductItemComponentPreview(){
    ProductItemComponent(
        productItem = productItem(
            product_name = "Test Product",
            product_type = "Electronics",
            price = "500",
            tax = "18",
            files = "hey.com"
        )
    )
}