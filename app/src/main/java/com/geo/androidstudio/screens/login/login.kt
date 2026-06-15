package com.geo.androidstudio.screens.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.geo.androidstudio.R
import com.geo.androidstudio.navigation.ROUTE_DASHBOARD
import com.geo.androidstudio.navigation.ROUTE_REGISTER
import com.geo.androidstudio.viewmodel.AuthViewModel

@Composable
fun LoginScreen(navController: NavHostController){
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    //
    val myauth = remember { AuthViewModel (navController,context)}
    Column(
        modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
        .background(Color.White)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Welcome to Home.",
            color = Color.Blue,
            fontSize = (28.sp),
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(28.dp))
        Image(
            painter = painterResource(id = R.drawable.tire),
            contentDescription = "Logo",
            modifier = Modifier.size(200.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(28.dp))
        Text(
            "Already Have an account Login Here!",
            color = Color.Blue,
            fontSize = (20.sp),
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(28.dp))
        var email by remember { mutableStateOf(value = "") }
        var password by remember { mutableStateOf(value = "") }
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Enter E-Mail Address") },
            placeholder = { Text("Enter E-Mail Address") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Email Icon"
                )
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(28.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Enter Password") },
            placeholder = { Text("Enter Password") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Password Icon"
                )
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(28.dp))

        Button(
            onClick = {
                if (email.isBlank() || password.isBlank() )
                {
                    Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                } else {
                    myauth.login(email,password)
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue,
                contentColor = Color.White
            )
        )
        {
            Text("Login",
                fontSize = 20.sp,)
        }
        Spacer(modifier = Modifier.height(28.dp))
        TextButton(onClick = {navController.navigate(ROUTE_REGISTER)}){
            Text(
                text = "Don't have an account? Register here",
                fontSize = 20.sp
            )
        }

    }
}
@Preview(showBackground = true)
@Composable
fun LoginScreenPreview(){
    LoginScreen(rememberNavController())
}