package com.geo.androidstudio.screens.dashboard


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.TopAppBarDefaults

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil3.compose.rememberAsyncImagePainter
import com.geo.androidstudio.models.Product
import com.geo.androidstudio.navigation.ROUTE_PRODUCTLIST
import com.geo.androidstudio.viewmodel.AuthViewModel
import com.geo.androidstudio.viewmodel.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDashboardScreen(navController: NavHostController){

    var context= LocalContext.current

    var productViewModel = ProductViewModel(navController,context)
    var authViewModel = AuthViewModel(navController, context)
    var searchText by remember { mutableStateOf("") }
    var product = remember {mutableStateOf(Product("","","","",""))}
    var products = remember {mutableStateListOf<Product>()}
    var username by remember {mutableStateOf("")}
    //fetch the products from product,products
    LaunchedEffect(Unit){
        productViewModel.allProducts(product,products)
        authViewModel.getCurrentUserName {username=it}
    }
    Scaffold(
        topBar = {
          TopAppBar(
                title ={ Text("Welcome to your Dashboard") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Gray,
                    titleContentColor = Color.Blue
                ),
            actions = {
                IconButton(onClick = {}) {
                    Icon(
                        Icons.Default.ShoppingCart,
                        contentDescription = "SHopping Cart Icon"
                    )
                }
                IconButton(onClick = {}) {
                    Icon(
                        Icons.Default.ExitToApp,
                        contentDescription = "Exit Button"
                    )
                }
            }
          )
        },
//        floating action button
        floatingActionButton = {
            FloatingActionButton(onClick = { ROUTE_PRODUCTLIST },
                containerColor = Color.Blue
                )
            {
                Icon(imageVector = Icons.Default.ShoppingCart,
                contentDescription = "Cart",
                tint=Color.Black)
            }
        },
        //bottom bar
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = true,
                    onClick = {},
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {},
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("My Profile") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {},
                    icon = { Icon(Icons.Default.Settings, contentDescription = "Settings") },
                    label = { Text("Settings") }
                )
            }
        },
    )
    {innerpadding ->
        Column (
            modifier = Modifier.padding(innerpadding)
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        )
        {
            Text(text = "Welcome ${username}!")
            Spacer(modifier = Modifier.height(16.dp))
            //Search bar
            OutlinedTextField(
                value = searchText,
                onValueChange = {searchText = it},
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Search Product") },
                placeholder = { Text("Search Product here") } ,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                        tint = Color.Green
                    )
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text="Featured Products",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp) )
             {
                items(products){product->
                    ProductCard(product)
                }
            }
        }
    }
}

@Composable
fun ProductCard(product: Product) {
    Card(
        modifier = Modifier
        .width(260.dp), elevation = CardDefaults.cardElevation(8.dp) )

    {
      Column(
          modifier = Modifier.padding(8.dp)
      )
        { Image (
                painter = rememberAsyncImagePainter(product.imageUrl),
                contentDescription = "Products",
                contentScale = ContentScale.Crop,modifier = Modifier.fillMaxWidth().height(130.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            //name
            Text(text = product.name,
                maxLines = 1,
                style = MaterialTheme.typography.titleMedium)
            //price
            Text(text = "Kshs${product.price}",
                maxLines = 1)
            //description
            Text(text = product.description,
                maxLines = 2)
      }
    }
}


@Preview(showBackground = true)
@Composable
fun UserDashboardScreenPreview(){
    UserDashboardScreen(rememberNavController())
}
