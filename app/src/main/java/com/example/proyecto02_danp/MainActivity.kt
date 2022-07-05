package com.example.proyecto02_danp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Firebase.firestore

        db.collection("endangered_animals")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.e("Objeto", "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w("Error", "Error getting documents.", exception)
            }
    }
}