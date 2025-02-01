package com.samapp.getswipeinternshipassigment.presentation.productAddition

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
//import coil3.Uri
import com.samapp.getswipeinternshipassigment.data.remote.api.productApiService
import com.samapp.getswipeinternshipassigment.domain.model.productItem
import com.samapp.getswipeinternshipassigment.presentation.productAddition.components.PhotoPickingComponent
import com.samapp.getswipeinternshipassigment.presentation.productAddition.components.convertUriToFile
import com.samapp.getswipeinternshipassigment.presentation.productAddition.components.prepareTextField
import org.koin.androidx.compose.koinViewModel

@Composable
fun productAdditionScreen(
    onDismiss: () -> Unit,
    onProductAdded: () -> Unit,
    viewModel: productAdditionViewModel
) {
    val state = viewModel.state.value
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "Add New Product", style = MaterialTheme.typography.headlineSmall)


        Spacer(modifier = Modifier.height(8.dp))

        var name by remember { mutableStateOf("") }
        var type by remember { mutableStateOf("") }
        var price by remember { mutableStateOf("") }
        var photoUri by remember { mutableStateOf<Uri?>(null) }
        PhotoPickingComponent (
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ){ uri->
            val imagePart = uri?.let { convertUriToFile(it, context) }
            photoUri = uri
        }

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Product Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = type,
            onValueChange = { type = it },
            label = { Text("Type") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = price,
            onValueChange = { price = it },
            label = { Text("Price") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if(name.isEmpty() || type.isEmpty() || price.isEmpty()){
                    Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                    return@Button
                }
                viewModel.addProduct(productItem(
                    product_name = name,
                    product_type = type,
                    price = price,
                    tax = "18",
                    files = ""
                ))
                onProductAdded()
                onDismiss()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = Color.White
                )
            }else {
                Text("Add Product")
            }
        }
    }
}
