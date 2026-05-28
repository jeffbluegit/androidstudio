package com.geo.androidstudio.screens.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.geo.androidstudio.R
import com.geo.androidstudio.navigation.ROUTE_LOGIN

@Composable
fun RegisterScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {
        Text(
            "Register Here",
            fontSize = (28.sp),
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Cursive,
            fontStyle = FontStyle.Normal,
            color = Color.Blue
        )
        Spacer(modifier = Modifier.height(28.dp))
        Text("Already have an account? Login Here!",
            fontSize = (20.sp),
            color= Color(0xFF6200EE),
            )
            Image(
                painter = painterResource(id = R.drawable.tire),
                contentDescription = "Logo",
                modifier = Modifier.size(200.dp)
                    .clip(CircleShape)
            )
        Spacer(modifier = Modifier.fillMaxWidth())

        var fullname by remember { mutableStateOf(value = "") }
        var email by remember { mutableStateOf(value = "") }
        var password by remember { mutableStateOf(value = "") }
        var confirmPassword by remember { mutableStateOf(value = "") }
        //Outline TextField FUll NAME
        OutlinedTextField(
            value = fullname,
            onValueChange = { fullname = it },
            label = { Text("Enter FUll NAME") },
            placeholder = { Text("John doe") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Person Icon"
                )
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(28.dp))
        //Outline text field E-Mail
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

        //OUTLINE TEXT FIELD PASSWORD
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

        //outfield text confirm password
        Spacer(modifier = Modifier.height(28.dp))

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword= it },
            label = { Text("Confirm Your Password") },
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
                //connect to firebase
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
        TextButton(onClick = {navController.navigate(ROUTE_LOGIN)

        }) {Text("Don't have an account ? Register Here!")}

    }
}
@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview(){
    RegisterScreen(rememberNavController())
}