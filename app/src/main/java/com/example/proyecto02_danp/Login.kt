package com.example.proyecto02_danp

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.launch

class Login : AppCompatActivity() {
    private val Context.dataStore by preferencesDataStore(DataStore.PREFS_NAME)
    lateinit var notePrefsData: DataStore
    override fun onCreate(savedInstanceState: Bundle?) {
        notePrefsData = DataStore(dataStore)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        getData()
    }
    private fun getData(){
        buttonLogin.setOnClickListener{
            if (emailLogin.text.isNotEmpty()&&password.text.isNotEmpty()){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(emailLogin.text.toString(),
                password.text.toString()).addOnCompleteListener{
                    if(it.isSuccessful){
                        saveToken((it.result?.user?.uid).toString())

                        //val intentLogin = Intent(this, Login::class.java)
                        //startActivity(intentLogin)
                    } else{
                        val msg="USUARIO NO REGISTRADO"
                        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
    private fun saveToken(idUserA: String) {
        lifecycleScope.launch {
            notePrefsData.saveidUSER(idUserA)
        }
    }
}