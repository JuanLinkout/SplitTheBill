package com.example.splitthebill.presentation.customerdetails

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.splitthebill.databinding.FragmentCustomerBillDetailsBinding
import com.example.splitthebill.domain.entities.customers.CustomerBillDetails
import com.example.splitthebill.domain.entities.navigation.CustomerBillTypeEnum
import com.example.splitthebill.presentation.adapters.OrderItemAdapater

class CustomerBillDetailsFragment : Fragment() {
    private val args: CustomerBillDetailsFragmentArgs by navArgs()
    private lateinit var binding: FragmentCustomerBillDetailsBinding
    private lateinit var viewModel: CustomerBillDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCustomerBillDetailsBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(
            this,
            CustomerBillDetailsViewModel.Factory()
        )[CustomerBillDetailsViewModel::class.java]

        if (args.type == CustomerBillTypeEnum.EDIT) {
            viewModel.fetchDetails(args.customerId)
            val observer = Observer<CustomerBillDetails> { customerDetails ->
                binding.customerBillRecyclerView.adapter =
                    OrderItemAdapater(customerDetails.orderItems.toTypedArray())
                binding.customerBillRecyclerView.layoutManager = LinearLayoutManager(binding.root.context)

                binding.customerNameEditText.setText(customerDetails.customerName)
                binding.confirmButton.text = "Confirmar edição"

                binding.confirmButton.setOnClickListener {
                    viewModel.createOrUpdate(
                        CustomerBillDetails(
                            customerName = binding.customerNameEditText.text.toString(),
                            orderItems = emptyList(),
                            id = customerDetails.id
                        )
                    )
                }
            }
            viewModel.customerBillDetails.observe(viewLifecycleOwner, observer)

        } else if (args.type == CustomerBillTypeEnum.CREATE) {
            binding.confirmButton.text = "Confirmar criação"
            binding.addOrderButton.visibility = View.GONE
            binding.confirmButton.setOnClickListener {
                viewModel.createOrUpdate(
                    CustomerBillDetails(
                        customerName = binding.customerNameEditText.text.toString(),
                        orderItems = emptyList(),
                        id = null
                    )
                )
            }
        }

        return binding.root
    }
}