package com.geo.androidstudio.screens.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.geo.androidstudio.R

@OptIn(ExperimentalMaterial3Api::class)
    @Composable
fun DashBoardScreen(){
    Scaffold(
        //TOP BAR
        topBar ={
            TopAppBar(
                title = { Text("Dashboard")},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Magenta,
                    titleContentColor = Color.Blue
                ),
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Settings,
                            contentDescription = "Settings Icon"
                        )
                    }
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Person,
                            contentDescription = "Person Icon"
                        )
                    }
                    /*IconButton(onClick = {}) {
                        Icon(
                            Icons.Default.Settings,
                            contentDescription = "Settings Icon"
                        )
                    }*/
                    IconButton(onClick = {}) {
                        Icon(
                            Icons.Default.ExitToApp,
                            contentDescription = "ExitIcon"
                        )
                    }
                }
            )
        },
        //END of TOP BAR.
        //Beginning of the bottom bar
        bottomBar = {
            BottomAppBar(
                containerColor = Color.White,
                contentColor = Color.Black
            )
            {
                Text("Bottom Bar")
            }
        },
        //END of bottom bar
        //FLOATING ACTION BUTTON
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(Icons
                    .Default.Add,
                    contentDescription = "Add Icon")
            }

        }
    )
    {
        innerpadding ->
        Column(
            modifier = Modifier
                .padding(innerpadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Surface(
                shape = RoundedCornerShape(16.dp),
                shadowElevation = 8.dp,
                color = Color.LightGray,
                modifier = Modifier
                    .height(100.dp)
                    .width(150.dp)
            ){
                Image(
                    painter = painterResource(id = R.drawable.tech),
                    contentDescription = "Banner",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
            Text(text = "Dashboard Screen")
        }

    }
}
@Preview
@Composable
fun DashBoardScreenPreview(){
    DashBoardScreen()
}