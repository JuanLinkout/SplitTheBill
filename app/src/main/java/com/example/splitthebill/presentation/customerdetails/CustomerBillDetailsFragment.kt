package com.example.splitthebill.presentation.customerdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.splitthebill.databinding.FragmentCustomerBillDetailsBinding
import com.example.splitthebill.domain.entities.customers.CustomerBillDetails
import com.example.splitthebill.domain.entities.navigation.CustomerBillTypeEnum
import com.example.splitthebill.domain.entities.navigation.OrderItemTypeEnum
import com.example.splitthebill.domain.entities.orderitem.OrderItem
import com.example.splitthebill.infra.room.entities.Order
import com.example.splitthebill.presentation.adapters.OrderItemAdapater
import com.example.splitthebill.presentation.customers.CustomersBillsFragment

class CustomerBillDetailsFragment : Fragment() {
    private val args: CustomerBillDetailsFragmentArgs by navArgs()
    private lateinit var binding: FragmentCustomerBillDetailsBinding
    private val viewModel by viewModels<CustomerBillDetailsViewModel>(factoryProducer = { CustomerBillDetailsViewModel.Factory() })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCustomerBillDetailsBinding.inflate(layoutInflater)

        if (args.type == CustomerBillTypeEnum.EDIT) {
            viewModel.fetchDetails(args.customerId)
            val observer = Observer<CustomerBillDetails> { customerDetails ->
                binding.customerBillRecyclerView.adapter =
                    OrderItemAdapater(
                        customerDetails.orderItems.toTypedArray(),
                        args.customerId.toLong()
                    )
                binding.customerBillRecyclerView.layoutManager =
                    LinearLayoutManager(binding.root.context)

                binding.customerNameEditText.setText(customerDetails.customerName)
                binding.confirmButton.text = "Confirmar edição"
                binding.addOrderButton.setOnClickListener {
                    val action =
                        CustomerBillDetailsFragmentDirections.actionCustomerBillDetailsFragmentToOrderItemDetails(
                            null,
                            OrderItemTypeEnum.CREATE,
                            args.customerId.toLong()
                        )
                    findNavController().navigate(action)
                }

                binding.confirmButton.setOnClickListener {
                    viewModel.createOrUpdate(
                        CustomerBillDetails(
                            customerName = binding.customerNameEditText.text.toString(),
                            orderItems = emptyList(),
                            id = customerDetails.id
                        )
                    )
                    findNavController().popBackStack()
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
                findNavController().popBackStack()
            }
        }

        return binding.root
    }
}