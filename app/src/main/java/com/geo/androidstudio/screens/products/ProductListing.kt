package com.geo.androidstudio.screens.products

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.geo.androidstudio.R
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import com.geo.androidstudio.models.Product
import com.geo.androidstudio.navigation.`ROUTE_ADD-PRODUCT`
import com.geo.androidstudio.navigation.ROUTE_UPDATEPRODUCT
import com.geo.androidstudio.viewmodel.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Products") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Magenta
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(`ROUTE_ADD-PRODUCT`)
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add Product")
            }
        }
    ) { innerpadding ->

        val product = remember { mutableStateOf(Product("", "", "", "")) }
        val products = remember { mutableStateListOf<Product>() }

        val context = LocalContext.current
        val myproductviewmodel = remember { ProductViewModel(navController, context) }

        LaunchedEffect(Unit) {
            myproductviewmodel.allProducts(product, products)
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerpadding)
        ) {
            items(products) { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    // Start of Column with 12.dp padding
                    Column(modifier = Modifier.padding(12.dp)) {
                        AsyncImage(
                            model = item.imageUrl.ifEmpty { R.drawable.kentucky },
                            contentScale = ContentScale.Crop,
                            contentDescription = "Product Image",
                            placeholder = painterResource(R.drawable.kentucky),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = item.name,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Text(
                            text = "Kshs ${item.price}",
                            color = Color.Red,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                        )
                        Text(
                            text = item.description,
                            style = MaterialTheme.typography.bodyMedium
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        // FIXED: Row is now inside the padded Column
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            OutlinedButton(
                                onClick = {
                                    navController.navigate("$ROUTE_UPDATEPRODUCT/${item.id}")
                                },
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Update")
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Button(
                                onClick = {
                                    myproductviewmodel.deleteProduct(item.id)
                                },
                                modifier = Modifier.weight(1f),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.Red
                                )
                            ) {
                                Text("Delete", color = Color.White)
                            }
                        }
                    } // End of Column
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductListScreenPreview() {
    ProductListScreen(rememberNavController())
}
