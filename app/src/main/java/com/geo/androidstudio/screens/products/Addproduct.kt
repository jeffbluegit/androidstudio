package com.geo.androidstudio.screens.products

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.material3.Button
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun AddProductScreen(navController: NavHostController) {
    Scaffold(
        //Top Bar
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Add Product") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor =   Color.Gray,
                    )
            )
        },
    )
    { innerpadding ->
        //Column
        Column(
            modifier = Modifier
                .padding(innerpadding)
                .fillMaxSize(),

            ) {
            Text(
                "ADD NEW PRODUCT",
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFFFFA500),
//                verticalArrangement = Arrangement.Center,
            )
                Spacer(modifier = Modifier.height(28.dp))
                var productName by remember { mutableStateOf(value = "") }
                var productPrice by remember { mutableStateOf(value = "") }
            OutlinedTextField(
                value = productName,
                onValueChange = { productName = it },
                label = { Text("Enter Product Name") },
                modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                value = productPrice,
                onValueChange = { productPrice = it },
                label = { Text("Enter Product Price") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))

            //ADD OUTLINED TEXT_FIELD FOR PRICE, DESCRIPTION, AND CATEGORY
            //product image picker + previewer
            Button(
                onClick= {},
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add Product")
            }
        }



    }


}
@Preview(showBackground = true)
@Composable
fun AddProductScreenPreview(){
    AddProductScreen(rememberNavController())
}