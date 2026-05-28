package com.geo.androidstudio.screens.practice

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FirstScreen(){
    Column(
        modifier= Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Welcome to the new app",
            fontFamily= FontFamily.Monospace,
            fontSize= 20.sp,
            fontWeight = FontWeight.SemiBold,

        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Kotlin is an android programming language",
            fontFamily= FontFamily.Monospace,
            fontSize= 20.sp,
            fontWeight = FontWeight.SemiBold,

        )
    }
}
@Preview
@Composable
fun FirstScreenPreview(){
    FirstScreen()
}