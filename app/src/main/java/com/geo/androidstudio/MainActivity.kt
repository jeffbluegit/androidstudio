package com.geo.androidstudio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.geo.androidstudio.navigation.AppNavHost
import com.geo.deepseek.screens.practice.BoxScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppNavHost()



        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxSize()
            .background(Color.Cyan)
    ) {
        Text(
            text = "Hello $name!",
            modifier = modifier,
            fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )
        Text(
            text = "I love programming",
            fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight.SemiBold,
            color = Color.Blue
        )
    }

}


