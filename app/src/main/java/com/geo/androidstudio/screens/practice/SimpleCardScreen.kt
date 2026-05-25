package com.geo.deepseek.screens.practice

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.CardDefaults.cardElevation
//import androidx.compose.material3.CheckboxDefaults.colors
//import androidx.compose.material3.DrawerDefaults.shape
//import androidx.compose.material3.FloatingActionButtonDefaults.elevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.geo.androidstudio.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SimpleCardScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) { }
    Card(
        modifier = Modifier.width(180.dp)
            .height(200.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text("This is a simple card")
            Image(
                painter = painterResource(id = R.drawable.winter),
                contentDescription = " image",
                modifier = Modifier.fillMaxWidth()
                    .height(130.dp)
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun SimpleCardScreenPreview(){
    SimpleCardScreen()
}