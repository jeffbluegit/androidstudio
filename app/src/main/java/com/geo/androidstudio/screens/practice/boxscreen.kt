package com.geo.deepseek.screens.practice

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.geo.androidstudio.R

@Composable
fun BoxScreen(){
    Box(modifier = Modifier.fillMaxSize()) {
        Image(painter = painterResource(id = R.drawable.winter),
            contentDescription = "Winter Season",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Text("Plaraglam",
            fontSize = 24.sp,
            fontFamily = FontFamily.Serif,
            color = Color.Red,
            modifier=Modifier.align(Alignment.Center)
        )
    }
}
@Preview(showBackground = true)
@Composable
fun BoxScreenPreview(){
    BoxScreen()
}