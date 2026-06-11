package com.geo.androidstudio.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.geo.androidstudio.screens.dashboard.DashBoardScreen
import com.geo.androidstudio.screens.login.LoginScreen
import com.geo.androidstudio.screens.practice.HobbyScreen
import com.geo.androidstudio.screens.products.AddProductScreen
import com.geo.androidstudio.screens.products.ProductListScreen
import com.geo.androidstudio.screens.products.UpdateProductScreen
import com.geo.androidstudio.screens.register.RegisterScreen
import com.geo.androidstudio.screens.splashscreen.SplashScreen

@Composable
fun AppNavHost(
    modifier: Modifier=Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination:String= ROUTE_SPLASHSCREEN,
    ) {
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = startDestination
    ) {
        composable(ROUTE_SPLASHSCREEN) {
            SplashScreen(navController)
        }
        composable(ROUTE_LOGIN) {
          LoginScreen(navController)
        }
        composable(ROUTE_DASHBOARD) {
            DashBoardScreen(navController)
        }
        composable(ROUTE_REGISTER) {
            RegisterScreen(navController)
        }
        composable(`ROUTE_ADD-PRODUCT`) {
            AddProductScreen(navController)
        }
        composable(ROUTE_HOBBIES) {
            HobbyScreen(navController)
        }
        composable(ROUTE_PRODUCTLIST) {
            ProductListScreen(navController)
        }
        composable(ROUTE_UPDATEPRODUCT + "/{productId}") {backStackEntry->
            val productId=backStackEntry.arguments?.getString("productId")
            UpdateProductScreen(navController, productID = "$productId")
        }
    }

}