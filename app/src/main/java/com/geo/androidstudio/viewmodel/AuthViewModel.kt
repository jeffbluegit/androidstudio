package com.geo.androidstudio.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavHostController
import com.geo.androidstudio.models.User
import com.geo.androidstudio.navigation.ROUTE_DASHBOARD
import com.geo.androidstudio.navigation.ROUTE_LOGIN
import com.geo.androidstudio.navigation.ROUTE_REGISTER
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

class AuthViewModel(var navController: NavHostController, var context: Context) {

    private var mAuth = FirebaseAuth.getInstance()

    // Register Function
    fun signup(fullname: String, email: String, password: String, confirmpassword: String) {
        // Validation check
        if (fullname.isBlank()||email.isBlank() || password.isBlank() || confirmpassword.isBlank()) {
            Toast.makeText(context, "All files need to be filled.", Toast.LENGTH_SHORT).show()
            return
        } else if (password != confirmpassword) {
            Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
            return
        } else {

            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val currentUser = mAuth.currentUser
                        val userId = currentUser?.uid
                        if (userId != null) {
                            val userdata = User(fullname, email, password, userId)

                            //save user to realtime database
                            val regRef = FirebaseDatabase.getInstance().getReference()
                                .child("Users/$userId")
                            regRef.setValue(userdata).addOnCompleteListener { dbTask ->
                                if (dbTask.isSuccessful) {
//                            save user to firestore
                                    FirebaseFirestore.getInstance()
                                        .collection("Users")
                                        .document(userId)
                                        .set(userdata)
                                        .addOnCompleteListener { firestoreTask ->
                                            if (firestoreTask.isSuccessful) {
                                                Toast.makeText(
                                                    context, "User registered successfully",
                                                    Toast.LENGTH_LONG
                                                ).show()
                                                navController.navigate(ROUTE_LOGIN)
                                            }
                                        }
                                } else {
                                    Toast.makeText(
                                        context,
                                        "DB Error:${dbTask.exception?.message}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        } else {
                            Toast.makeText(
                                context,
                                "Auth Error: ${task.exception?.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                            navController.navigate(ROUTE_REGISTER)
                        }
                    }
                }
        }
    }
        // Login Function
    fun login(email: String, password: String) {
            if (email.isBlank() || password.isBlank()) {
                Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return
            }

            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                 Toast.makeText(context, "Successfully Logged in", Toast.LENGTH_SHORT).show()
                    navController.navigate(ROUTE_DASHBOARD) {
                        // Pop up to login screen so pressing back button doesn't log them back out
                        popUpTo(ROUTE_LOGIN) { inclusive = true }
                    }
                } else {
                    Toast.makeText(context, "Error: ${task.exception?.message}", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
        // Logout Function
    fun logout() {
            mAuth.signOut()
            navController.navigate(ROUTE_LOGIN)
            { popUpTo(0) { inclusive = true } }
        }
        //Get CurrentUsername function
    fun getCurrentUserName(onResult: (String) -> Unit) {
            val userId = mAuth.currentUser?.uid ?: run {
                onResult("User")
                return
            }
            FirebaseDatabase.getInstance().getReference("Users/$userId")
                .get()
                .addOnSuccessListener { snapshot ->
                    onResult(snapshot.child("fullname").getValue(String::class.java) ?: "User")
                }
                .addOnFailureListener {
                    onResult("User")
                }
        }
}
