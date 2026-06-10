package com.geo.androidstudio.screens.products

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateProductScreen(navController: NavHostController){
    var productName by remember { mutableStateOf("")}
    var price by remember { mutableStateOf("")} }
    var description by remember {mutableStateof("")
    var imageUrl by remember {mutableStateOf<String?>(null)}
        //Image Picker
        val imagePickerLauncher=rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent(),
        )
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {Text("Update Product")},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Red
                )
            )

        },
    ){
        innerpadding->
        //Column
        Column(
            Modifier
                .padding(innerpadding)
                .verticalScroll(rememberScrollState())
                .fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally
        ){
            Text(
                "Product Name",
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFFFFA500),
//                verticalArrangement = Arrangement.Center,
            )
            Spacer(modifier = Modifier)
            OutlinedTextField(
                value = productName,
                onValueChange = { productName = it },
                label = { Text("Enter Product Name") },
                modifier = Modifier.fillMaxWidth()
            ){}
        }
    }
}