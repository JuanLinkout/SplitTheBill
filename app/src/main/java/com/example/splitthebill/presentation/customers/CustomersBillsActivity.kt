package com.example.splitthebill.presentation.customers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.splitthebill.databinding.ActivityCustomersScreenBinding
import com.example.splitthebill.domain.entities.customers.CustomerBill
import com.example.splitthebill.presentation.adapters.CustomerBillAdapter
import com.example.splitthebill.presentation.utils.StatusBarUtil

class CustomersBillsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCustomersScreenBinding
    private lateinit var viewModel: CustomersBillsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomersScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            CustomersBillsViewModel.Factory()
        )[CustomersBillsViewModel::class.java]

        val observer = Observer<List<CustomerBill>> {
            binding.customerBillRecyclerView.adapter = CustomerBillAdapter(it.toTypedArray())
        }
        viewModel.customerBills.observe(this, observer)

        val statusBarHeight = StatusBarUtil.getStatusBarHeight(binding.root.context)
        binding.customerBillRecyclerView.setPadding(16, 16 + statusBarHeight, 16, 16)
        binding.customerBillRecyclerView.layoutManager = LinearLayoutManager(binding.root.context)
    }
}