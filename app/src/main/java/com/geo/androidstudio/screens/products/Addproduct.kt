package com.geo.androidstudio.screens.products

import android.R.attr.onClick
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.geo.androidstudio.R
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun AddProductScreen(navController: NavHostController) {
    var productName by remember { mutableStateOf(value = "") }
    var productPrice by remember { mutableStateOf(value = "") }
    var description by remember {mutableStateOf("")}
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ){uri: Uri? ->
        imageUri = uri
    }




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
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

            ) {
            Text(
                "ADD NEW PRODUCT",
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFFFFA500),
//                verticalArrangement = Arrangement.Center,
            )
                Spacer(modifier = Modifier.height(28.dp))

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
            //Image preview
            Card(
                shape = CircleShape,
                modifier = Modifier
                .size(200.dp)
                .clickable{imagePickerLauncher.launch("image/*")}
            ){
                AsyncImage(
                    model = imageUri ?:R.drawable.spring,
                    contentDescription = "FC",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

            }

            //ADD OUTLINED TEXT_FIELD FOR PRICE, DESCRIPTION, AND CATEGORY
            //product image picker + previewer
            OutlinedButton(onClick = {imagePickerLauncher.launch("image/*")},
                border = BorderStroke(2.dp, Color(0xFF6200EE)),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color(0xFF6200EE),
                    containerColor = Color.Transparent,
                    disabledContentColor = Color.LightGray
                )
                // shape = C
            ) {
                Text("Choose Image")
            }
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