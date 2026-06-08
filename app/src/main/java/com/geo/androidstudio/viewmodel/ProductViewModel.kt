package com.geo.androidstudio.viewmodel



import com.geo.androidstudio.R
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavHostController
import com.geo.androidstudio.models.Product
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.io.InputStream

class ProductViewModel (navController: NavHostController,var context: Context){

    var cloudinaryUrl="https://api.cloudinary.com/v1_1/dojp0mlml/upload" //do...use own cloud name
    var uploadPreset="NewProducts"
    val databaseReference= FirebaseDatabase.getInstance().getReference("Products")
    //functions
    //crud c-create,r-read,u-update,d-delete
    //upload product  to firebase function
    fun uploadProduct(imageUri: Uri?,name:String,price:String,description:String){
        val ref = databaseReference.push()
        val currentUser= FirebaseAuth.getInstance().currentUser
        val userId=currentUser?.uid ?: ""
        CoroutineScope(Dispatchers.IO).launch {
            try{
                val imageUrl=if(imageUri!=null){
                    uploadToCloudinary( context,imageUri)
                }else{
                    ""
                }
                val productData=mapOf(
                    "id" to ref.key,
                    "name" to name,
                    "price" to price,
                    "description" to description,
                    "imageUrl" to imageUrl,
                    "userId" to userId,
                )
                ref.setValue(productData).addOnCompleteListener{
                    if(it.isSuccessful){
                        Toast.makeText(context,"Product uploaded successfully",Toast.LENGTH_SHORT).show()
                        //navigate to product list
                    }else{
                        Toast.makeText(context,"Error:${it.exception?.message}",Toast.LENGTH_SHORT).show()

                    }
                }
            }catch (e: Exception){
                CoroutineScope(Dispatchers.Main).launch{
                    Toast.makeText(context,"Error:${e.message}",Toast.LENGTH_LONG).show()
                }
            }
        }

    }
    //upload image to cloudinary function using OkHttp
    //extracts the secure image url from the response and returns the url
    private fun uploadToCloudinary(context: Context, uri: Uri): String {
        val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
        val fileBytes = inputStream?.readBytes()
            ?: throw Exception("Image read failed")
        val requestBody = MultipartBody.Builder().setType(MultipartBody.FORM)
            .addFormDataPart(
                "file",
                "image.jpg",
                RequestBody.create("image/*".toMediaTypeOrNull(), fileBytes)
            )
            .addFormDataPart("upload_preset", uploadPreset)
            .build()
        val request = Request.Builder()
            .url(cloudinaryUrl)
            .post(requestBody)
            .build()
        val response = OkHttpClient().newCall(request).execute()
        if (!response.isSuccessful) throw Exception("Upload failed")
        val responseBody = response.body?.string()
        val secureUrl = Regex("\"secure_url\":\"(.*?)\"")
            .find(responseBody ?: "")?.groupValues?.get(1)
        return secureUrl ?: throw Exception("Failed to get image URL")
    }


    //fetch product function
    fun allProducts(
        product: MutableState<Product>,
        products: SnapshotStateList<Product>
    )    :SnapshotStateList<Product>{
        //listener to the database reference to read data in realtime
        databaseReference.addValueEventListener(object: ValueEventListener {
            //Method trigger if there is any data changes in the database
            override fun onDataChange(Snapshot: DataSnapshot) {
                products.clear()
                for (snap in Snapshot.children){
                    val retrievedProduct=snap.getValue(Product::class.java)
                    if(retrievedProduct!=null){
                        product.value=retrievedProduct
                        products.add(retrievedProduct)
                    }
                }
            }
//Method if there's an error
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context,"Database Error", Toast.LENGTH_SHORT).show()
            }

        })
        return products
    }
//update product function
    //delete product function

}