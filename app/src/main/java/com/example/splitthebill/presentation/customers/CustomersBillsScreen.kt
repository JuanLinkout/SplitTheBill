package com.example.splitthebill.presentation.customers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.splitthebill.databinding.ActivityCustomersScreenBinding

class CustomersBillsScreen : AppCompatActivity() {
    private lateinit var binding: ActivityCustomersScreenBinding
    private val viewModel =
        ViewModelProvider(this, CustomersBillsViewModel.Factory())[CustomersBillsViewModel::class.java]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomersScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}