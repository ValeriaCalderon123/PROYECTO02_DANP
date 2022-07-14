package com.example.proyecto02_danp

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_configuration.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch

class ConfigurationActivity : AppCompatActivity() {
    private val Context.dataStore by preferencesDataStore(DataStore.PREFS_NAME)
    lateinit var notePrefsData: DataStore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        notePrefsData = DataStore(dataStore)
        setContentView(R.layout.activity_configuration)
        val spinner_colors = findViewById<Spinner>(R.id.spinner_colors)
        var colorVar=""
        var fontVar=""
        val spinner_fuentes = findViewById<Spinner>(R.id.spinner2)
        val lista_colors = resources.getStringArray(R.array.colors)
        val lista_fuentes = resources.getStringArray(R.array.fuente)
        val adapter_colors = ArrayAdapter(this, android.R.layout.simple_spinner_item,lista_colors)
        val adapter_fuentes = ArrayAdapter(this, android.R.layout.simple_spinner_item,lista_fuentes)
        spinner_colors.adapter = adapter_colors
        spinner_fuentes.adapter = adapter_fuentes
        var layoutConst: ConstraintLayout = findViewById(R.id.principalConfiguration)

        layoutConst.setBackgroundColor(Color.WHITE)

        //PREDEFINIR EL GUARDADO
        lifecycleScope.launch {
            notePrefsData.backgroundColor.collect { mycolor ->
                layoutConst.setBackgroundColor(Integer.parseInt(mycolor.toString()))
                //principal.setBackgroundColor(Integer.parseInt(mycolor.toString()))
            }
            notePrefsData.backgroundColor.collect { mycolor ->
            //    layoutConst
                textView4.text = mycolor.toString()
            }

        }

        saveconfiguration.setOnClickListener {
            if(colorVar=="Azul"){
                lifecycleScope.launch {

                    notePrefsData.saveBackground(Color.rgb(41,128,185).toString())

                }
            }else if (colorVar=="Anaranjado"){
                lifecycleScope.launch {
                    notePrefsData.saveBackground(Color.rgb(230,126,34).toString())

                }
            }else if (colorVar=="Rosado"){
                lifecycleScope.launch {
                    notePrefsData.saveBackground(Color.rgb(245,183,177).toString())

                }
            }else if (colorVar=="Blanco"){
                lifecycleScope.launch {
                    notePrefsData.saveBackground(Color.WHITE.toString())

                }
            }
        }
        spinner_colors.onItemSelectedListener = object:
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                colorVar= lista_colors[position];
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        spinner_fuentes.onItemSelectedListener = object:
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                fontVar= lista_fuentes[position];
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        cancelConfiguration.setOnClickListener {
            val intentLogOut = Intent(this, MainActivity::class.java)
            startActivity(intentLogOut)
        }
    }
}