package com.samapp.getswipeinternshipassigment.presentation.productList.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.samapp.getswipeinternshipassigment.R
import com.samapp.getswipeinternshipassigment.domain.model.productItem

@Composable
fun ProductItemTopSection(
    modifier: Modifier = Modifier,
    onFavoriteClick: () -> Unit,
    productItem: productItem
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .size(width = 160.dp, height = 100.dp)
    ) {
        // Product Image
        Image(
            painter = rememberAsyncImagePainter(
                model = productItem.files,
                error = painterResource(id = R.drawable.defaultimage),
                placeholder = painterResource(id = R.drawable.defaultimage)
            ),
            contentDescription = "Product Image",
            modifier = Modifier.
                size(width = 129.dp, height = 65.dp)
                .clip(RoundedCornerShape(10.dp))
                .align(Alignment.BottomCenter)
            ,
            contentScale = ContentScale.Crop
        )

        IconButton(
            onClick = { onFavoriteClick() },
            modifier = Modifier
                .size(40.dp)
                .align(Alignment.TopEnd)
                .padding(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = "Favorite",
                tint = Color.Black
            )
        }
    }
}
@Preview
@Composable
fun ProductItemTopSectionPreview(){
    ProductItemTopSection(
        productItem = productItem(
            product_name = "Test Product",
            product_type = "Electronics",
            price = "500",
            tax = "18",
            files = "hey.com"
        ),
        onFavoriteClick = {}
    )
}
