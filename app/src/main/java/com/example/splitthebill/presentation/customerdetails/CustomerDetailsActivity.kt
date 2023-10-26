package com.example.splitthebill.presentation.customerdetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.splitthebill.databinding.ActivityCustomerDetailsBinding
import com.example.splitthebill.domain.entities.customers.CustomerBillDetails

class CustomerDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCustomerDetailsBinding
    private lateinit var viewModel: CustomerBillDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomerDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            CustomerBillDetailsViewModel.Factory()
        )[CustomerBillDetailsViewModel::class.java]

        val observer = Observer<CustomerBillDetails> {
            // Fazer o binding com as informações aqui quando tiver o xml
        }
        viewModel.customerBillDetails.observe(this, observer)
    }
}