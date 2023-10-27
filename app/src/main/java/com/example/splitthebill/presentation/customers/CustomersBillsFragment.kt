package com.example.splitthebill.presentation.customers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.splitthebill.R
import com.example.splitthebill.databinding.FragmentCustomersBillsBinding
import com.example.splitthebill.domain.entities.customers.CustomerBill
import com.example.splitthebill.presentation.adapters.CustomerBillAdapter
import com.example.splitthebill.presentation.utils.StatusBarUtil

class CustomersBillsFragment : Fragment() {
    private lateinit var binding: FragmentCustomersBillsBinding
    private lateinit var viewModel: CustomersBillsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCustomersBillsBinding.inflate(layoutInflater)

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

        return binding.root
    }
}