package com.example.splitthebill.presentation.customerdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.splitthebill.databinding.FragmentCustomersBillsBinding
import com.example.splitthebill.domain.entities.customers.CustomerBillDetails

class CustomerBillDetailsFragment : Fragment() {
    private lateinit var binding: FragmentCustomersBillsBinding
    private lateinit var viewModel: CustomerBillDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCustomersBillsBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(
            this,
            CustomerBillDetailsViewModel.Factory()
        )[CustomerBillDetailsViewModel::class.java]

        val observer = Observer<CustomerBillDetails> {
            // Fazer o binding com as informações aqui quando tiver o xml
        }
        viewModel.customerBillDetails.observe(this, observer)

        return binding.root
    }
}