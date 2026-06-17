package com.geo.androidstudio.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.geo.androidstudio.models.onboardingItems
import com.geo.androidstudio.navigation.ROUTE_LOGIN
import com.geo.androidstudio.navigation.ROUTE_ONBOARDINGSCREEN
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(navController: NavHostController)
{
    val pagerState = rememberPagerState(pageCount = {onboardingItems.size} )
    val scope = rememberCoroutineScope ()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        //show dkip if not in the last page
        if (pagerState.currentPage != onboardingItems.lastIndex) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.End
            ){
                TextButton(onClick =  {navController.navigate(ROUTE_LOGIN)
                {
                    popUpTo(ROUTE_ONBOARDINGSCREEN) {
                    inclusive = true
                    }
                }
                }
                ){
                    Text("Skip")
                }
            }
        }else {
            Spacer(modifier = Modifier.height(48.dp) )
        }
        //slides
        HorizontalPager(
            state = pagerState,
            modifier = Modifier .weight(1f)
        ){
            page->
            val item = onboardingItems[page]
            Column(
                modifier = Modifier.padding(16.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ){
                Image(
                    painter = painterResource(id=item.imageResId),
                    contentDescription = null,
                    modifier = Modifier.size(200.dp)

                )
                Spacer(modifier = Modifier.height(20.dp) )
                Text(
                    text = item.title,
                    color = Color.Green,
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(
                    text = item.description,
                    color = Color.Blue,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp) )
//        indicators

//        get started button
        Button(onClick = {
            if (pagerState.currentPage==onboardingItems.lastIndex){
                navController.navigate(ROUTE_LOGIN){
                    popUpTo(ROUTE_ONBOARDINGSCREEN){
                        inclusive = true
                    }
                }
            }else{
                scope.launch { pagerState.animateScrollToPage(pagerState.currentPage+1) }
            }
        },
            modifier = Modifier.fillMaxWidth()
                .padding(32.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Green,
                contentColor = Color.White)
            
            )
        {
            Text(text = if(pagerState.currentPage==onboardingItems.lastIndex) "Get Started" else "Next")
        }
        Spacer( modifier = Modifier.height(60.dp) )
    }
      
}
@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    OnboardingScreen(rememberNavController() )

}
