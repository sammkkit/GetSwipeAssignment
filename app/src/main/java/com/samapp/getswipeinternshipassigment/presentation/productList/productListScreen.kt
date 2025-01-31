package com.samapp.getswipeinternshipassigment.presentation.productList

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.samapp.getswipeinternshipassigment.presentation.productAddition.productAdditionViewModel
import com.samapp.getswipeinternshipassigment.presentation.productList.components.ProductItemComponent
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProductListScreen() {
    val viewModel: ProductListViewModel = koinViewModel()
    val state by viewModel.state

    LaunchedEffect(state) {
        Log.d("TestScreen", "State updated: $state")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F4F4))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when {
            state.isLoading -> {
                CircularProgressIndicator()
            }
            state.error != null -> {
                Text(
                    text = "Error: ${state.error}",
                    color = Color.Red,
                    fontWeight = FontWeight.Bold
                )
            }
            state.products.isEmpty() -> {
                Text(
                    text = "No products available",
                    color = Color.Gray,
                    fontSize = 18.sp
                )
            }
            else -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(state.products) { product ->
                        ProductItemComponent(
                            productItem = product
                        )
                    }
                }
            }
        }
    }
}
