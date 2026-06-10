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
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.mutableStateListOf
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
import com.geo.androidstudio.R
import com.geo.androidstudio.models.Product
import com.geo.androidstudio.navigation.ROUTE_UPDATEPRODUCT
import com.geo.androidstudio.viewmodel.ProductViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(navController: NavHostController){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title ={Text("Products")},
                colors= TopAppBarDefaults.topAppBarColors(
                    containerColor=Color.Magenta
                )
            )
        }
    ){ innerpadding ->

        val product =remember{mutableStateOf(Product("", "", "", ""))}
        val products=remember { mutableStateListOf<Product>()}

        //fetch all products
        var context=LocalContext.current
        var myproductviewmodel= remember{ ProductViewModel(navController, context) }

        LaunchedEffect(Unit){
            myproductviewmodel.allProducts(product,products)
            //adding mock products as requested
            if(products.isEmpty()){
                products.add(Product(id="1",name = "Premium Bag", price="2500", description="Elegant Leather Bag",imageUrl=""))
                products.add(Product(id="2",name = "Running shoes", price="4500", description="Comfortable Sneakers for athletes",imageUrl=""))
            }
        }

        LazyColumn(modifier=Modifier.fillMaxSize().padding(innerpadding)){
            items(products){item->
                Card(
                    modifier=Modifier.fillMaxWidth().padding(8.dp),elevation= CardDefaults.cardElevation()
                )
                {
                    Column(modifier=Modifier.padding(12.dp)){
                        AsyncImage(
                            model=item.imageUrl.ifEmpty { R.drawable.kentucky },
                            contentScale = ContentScale.Crop,
                            contentDescription = "Product Image",
                            placeholder = painterResource(R.drawable.kentucky),
                            modifier=Modifier
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
                            text = "Kshs $item.price",
                            color = Color.Red,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                        )
                        Text(
                            text = item.description,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                    Row(
                        modifier=Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        OutlinedButton(
                            onClick = {
                                navController.navigate("$ROUTE_UPDATEPRODUCT/${item.id}")//navcontroller.navigate("update_product/${item.id}")
                            },
                            modifier=Modifier.weight(1f)
                        ){
                            Text("Update")
                        }
                        Spacer(modifier=Modifier.width(8.dp))
                        Button(
                            onClick = {
                                myproductviewmodel.deleteProduct(item.id)
                            },
                            modifier=Modifier.weight(1f),
                            colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                                containerColor = Color.Red
                            )
                        ){
                                Text("Delete", color = Color.White)
                            }

                    }



                    }
                }
            }
        }
    }
@Preview()
@Composable
fun ProductListScreenPreview(){
    ProductListScreen(rememberNavController())
}