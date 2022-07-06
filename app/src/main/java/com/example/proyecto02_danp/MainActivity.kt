package com.example.proyecto02_danp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab05_room.AnimalsViewModel
import com.example.proyecto02_danp.data.APIService
import com.example.proyecto02_danp.databinding.ActivityMainBinding

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