package com.example.splitthebill.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.splitthebill.R
import com.example.splitthebill.databinding.ActivityMainBinding
import com.example.splitthebill.di.AppModule
import com.example.splitthebill.di.AppModuleImplementation

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    companion object {
        lateinit var appModule: AppModule
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        appModule = AppModuleImplementation(this)
        setContentView(binding.root)
    }
}