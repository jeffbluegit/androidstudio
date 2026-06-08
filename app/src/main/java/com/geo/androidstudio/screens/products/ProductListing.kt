package com.geo.androidstudio.screens.products

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ProductListScreen(){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title ={Text("Products")},
                colors=TopAppBarDefaults.topAppBarColors(
                    containerColor=Color.Magenta
                )
            )
        }
    ){
        innerpadding->
        val product =remember{mutableStateOf(Product("","","","",""))}
        val products=remember{mutabelStateListOf<Proudct>()}
        //fetch all products
        var context=LocalContext.current
        var myproductviewmodel=ProductViewModel(navController,context))
        LaunchedEffect(Unit){
            myproductviewmodel.allProducts(product,products)
        }
        LazyColumn(modifier=Modifier.fillMaxSize()){
            items(products){item->
                Card(){
                        Text(text=item.name)
                        Text(text=item.price)
                        Text(text=item.description)
                    }
                }
            }
        }
    }
}@Preview()
@Composable
fun ProductListScreenPreview(){
    ProductListScreen()
}