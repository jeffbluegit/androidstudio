package com.geo.androidstudio.screens.practice

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun HobbyScreen(navController: NavHostController){val hobbies=listOf("Reading","Cooking","Dancing","Writing")
    LazyColumn(
        modifier= Modifier.fillMaxSize().padding(16.dp)
    )
    {
        items(hobbies){x->
            Card(
                modifier = Modifier.padding(16.dp)
                    .size(200.dp)
                    .height(100.dp)
                    .padding(16.dp)
            ){Text(text = x,
                fontSize = 24.sp,
                fontWeight= FontWeight.Bold,
                modifier=Modifier.padding(16.dp)

            )}
        }
    }

    }
@Preview(showBackground =true)
@Composable
fun HobbyScreenPreview(){
    HobbyScreen(rememberNavController() )
}
