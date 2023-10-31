package com.example.splitthebill.presentation.customerdetails

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.splitthebill.databinding.FragmentCustomerBillDetailsBinding
import com.example.splitthebill.domain.entities.customers.CustomerBillDetails
import com.example.splitthebill.domain.entities.navigation.CustomerBillTypeEnum
import com.example.splitthebill.domain.entities.navigation.OrderItemTypeEnum
import com.example.splitthebill.domain.entities.orderitem.OrderItem
import com.example.splitthebill.infra.room.entities.Order
import com.example.splitthebill.presentation.adapters.AdapterOnClickListener
import com.example.splitthebill.presentation.adapters.OrderItemAdapater
import com.example.splitthebill.presentation.customers.CustomersBillsFragment
import com.example.splitthebill.presentation.customers.CustomersBillsFragmentDirections

class CustomerBillDetailsFragment : Fragment() {
    private val args: CustomerBillDetailsFragmentArgs by navArgs()
    private lateinit var binding: FragmentCustomerBillDetailsBinding
    private val viewModel by viewModels<CustomerBillDetailsViewModel>(factoryProducer = { CustomerBillDetailsViewModel.Factory() })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCustomerBillDetailsBinding.inflate(layoutInflater)

        viewModel.validationError.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        }

        if (args.type == CustomerBillTypeEnum.EDIT) {
            viewModel.fetchDetails(args.customerId)
            val observer = Observer<CustomerBillDetails> { customerDetails ->
                binding.customerBillRecyclerView.adapter =
                    OrderItemAdapater(
                        customerDetails.orderItems.toTypedArray(),
                        AdapaterCallback()
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
                    val failed = viewModel.createOrUpdate(
                        customerName = binding.customerNameEditText.text.toString(),
                        id = customerDetails.id
                    )
                    if (!failed) {
                        findNavController().popBackStack()
                    }
                }
            }
            viewModel.customerBillDetails.observe(viewLifecycleOwner, observer)

        } else if (args.type == CustomerBillTypeEnum.CREATE) {
            binding.confirmButton.text = "Confirmar criação"
            binding.addOrderButton.visibility = View.GONE
            binding.confirmButton.setOnClickListener {
                val failed = viewModel.createOrUpdate(
                    customerName = binding.customerNameEditText.text.toString(),
                    id = null
                )
                if (!failed) {
                    findNavController().popBackStack()
                }
            }
        }

        return binding.root
    }

    inner class AdapaterCallback : AdapterOnClickListener {
        override fun onTileContactClick(position: Int) {
            val list = viewModel.customerBillDetails.value?.orderItems
            val orderItem = list?.find { it.id == list[position].id } ?: return
            val action =
                CustomerBillDetailsFragmentDirections.actionCustomerBillDetailsFragmentToOrderItemDetails(
                    orderItem,
                    OrderItemTypeEnum.EDIT,
                    args.customerId.toLong()
                )
            binding.root.findNavController().navigate(action)
        }

        @SuppressLint("NotifyDataSetChanged")
        override fun onRemoveClick(position: Int) {
            val list = viewModel.customerBillDetails.value?.orderItems
            val orderItem = list?.find { it.id == list[position].id } ?: return
            orderItem.id?.let {
                viewModel.delete(it.toInt(), args.customerId)
                binding.customerBillRecyclerView.adapter?.notifyDataSetChanged()
            }
        }
    }
}