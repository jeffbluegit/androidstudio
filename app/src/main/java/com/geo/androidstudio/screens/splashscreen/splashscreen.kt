package com.geo.androidstudio.screens.splashscreen

//import android.app.ProgressDialog.show
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.geo.androidstudio.R
import com.geo.androidstudio.navigation.ROUTE_LOGIN
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavHostController){

    //launch effects
    LaunchedEffect(true){
        delay(2000) //It delays two seconds
        navController.navigate(ROUTE_LOGIN)

    }
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
        contentAlignment = Alignment.Center

    ){
        Image(
            painter = painterResource(id = R.drawable.livin),
            contentDescription = "Logo",
            modifier = Modifier.size(180.dp)
        )
    }
}
@Preview(showBackground = true)
@Composable
fun SplashScreenPreview(){
    SplashScreen(rememberNavController())
}