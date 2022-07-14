package com.example.proyecto02_danp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab05_room.AnimalsViewModel
import com.example.proyecto02_danp.data.APIService
import com.example.proyecto02_danp.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var animalsViewModel: AnimalsViewModel
    lateinit var animalsAdapter: AnimalsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setupList()
        initRecyclerView()


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

          val item : MenuItem? = findViewById(R.id.logout);



        menuInflater.inflate(R.menu.custom_menu,menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //item.itemId.

        return when(item.itemId){

            R.id.login->{
                val intentLogin = Intent(this, Login::class.java)
                startActivity(intentLogin)
                return true
            }
            R.id.logout->{
                FirebaseAuth.getInstance().signOut()

                val intentLogOut = Intent(this, MainActivity::class.java)
                startActivity(intentLogOut)
                return true
            }
            R.id.configuration->{
                val intentCondiguracion = Intent(this, ConfigurationActivity::class.java)
                startActivity(intentCondiguracion)
                return true
            } else ->super.onOptionsItemSelected(item)
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setupViewModel() {
        val factory = AnimalsViewModelFactory(APIService())
        animalsViewModel = ViewModelProvider(this, factory).get(AnimalsViewModel::class.java)
    }

    private fun setupList() {
        animalsAdapter = AnimalsAdapter()
        rvListAnimals.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = animalsAdapter.withLoadStateHeaderAndFooter(
                header = AnimalsLoadStateAdapter { animalsAdapter.retry() },
                footer = AnimalsLoadStateAdapter { animalsAdapter.retry() }
            )
            setHasFixedSize(true)
        }

    }

    private fun initRecyclerView() {
        lifecycleScope.launch {
            animalsViewModel.pager.collectLatest { pagedData ->
                animalsAdapter.submitData(pagedData)
            }
        }
    }
}