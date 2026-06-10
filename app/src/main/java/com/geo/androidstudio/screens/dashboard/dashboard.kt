package com.geo.androidstudio.screens.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.geo.androidstudio.R
import com.geo.androidstudio.navigation.`ROUTE_ADD-PRODUCT`
import com.geo.androidstudio.navigation.ROUTE_HOBBIES
import com.geo.androidstudio.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
    @Composable
fun DashBoardScreen(navController: NavHostController){
    val context=LocalContext.current
    val myauth= AuthViewModel(navController, context)
    var selectedItem by remember { mutableStateOf(0) }

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
                    IconButton(onClick = {
                        //add logout code
                        myauth.logout()
                    }) {
                        Icon(
                            Icons.Default.ExitToApp,
                            contentDescription = "ExitIcon"
                        )
                    }
                }

            )
        },
        bottomBar = {
           /* BottomAppBar(
                containerColor = Color.Magenta,
                contentColor = Color.Black
            )
            {
                Text("Bottom Bar")
            }*/
            NavigationBar {
                NavigationBarItem(
                    selected = selectedItem == 0,
                    onClick = { selectedItem = 0 },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") }
                )
                NavigationBarItem(
                    selected = selectedItem == 1,
                    onClick = { selectedItem = 1 },
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("My Profile") }
                )
                NavigationBarItem(
                    selected = selectedItem == 2,
                    onClick = { selectedItem = 2 },
                    icon = { Icon(Icons.Default.Settings, contentDescription = "Settings") },
                    label = { Text("Settings") }
                )
            }
        },
        //END of bottom bar
        //FLOATING ACTION BUTTON
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(Icons
                    .Default.Add,
//                    .Color = Color.White,
                    contentDescription = "Add Icon")
            }

        }
    )
    {innerpadding ->
        Column(
            modifier = Modifier
                .padding(innerpadding)
                .padding(16.dp)
                .fillMaxSize()

            .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ){
            var username by remember { mutableStateOf("Loading...") }
            LaunchedEffect(Unit) {
                myauth.getCurrentUserName { username = it }
            }
            Surface(
                shape = RoundedCornerShape(16.dp),
                shadowElevation = 8.dp,
                color = Color.LightGray,
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
            )

            {
                Image(
                    painter = painterResource(id = R.drawable.tech),
                    contentDescription = "Banner",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
            Spacer(   modifier = Modifier.height(12.dp))
            Text(text = "Welcome $username")
            Spacer(   modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ){
                DashboardCard(
                    title = "ADD PRODUCTS",
                    modifier = Modifier.weight(1f),
                    onClick = {navController.navigate(`ROUTE_ADD-PRODUCT`)}
                )
                DashboardCard(
                    title = "PRODUCTS",
                    modifier = Modifier.weight(1f),
                    onClick = {navController.navigate(`ROUTE_ADD-PRODUCT`)}
                )
                DashboardCard(
                    title = "HOBBIES",
                    modifier = Modifier.weight(1f),
                    onClick = {navController.navigate(ROUTE_HOBBIES)}
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ){
                DashboardCard(
                    title = "DEEPSEEK",
                    onClick = {}
                )
                DashboardCard(
                    title = "PROFILE",
                    onClick = {}
                )

            }
        }


    }
}
@Preview
@Composable
fun DashBoardScreenPreview(){
    DashBoardScreen(rememberNavController())
}
//dashboard card
@Composable
fun DashboardCard(title: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Card( // Using Card instead of Surface for standard M3 look
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
            .width(100.dp) // Adjusted to fit 2 per screen comfortably
            .height(100.dp)
            .padding(horizontal = 4.dp)
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                color = Color(0xFF800000)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashBoardCardPreview(){
    DashboardCard(
        title = "Brave Browser",
        onClick = {}
    )
}
