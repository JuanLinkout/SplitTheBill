package com.example.splitthebill.presentation.orderitemdetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.splitthebill.R
import com.example.splitthebill.databinding.FragmentOrderItemDetailsBinding
import com.example.splitthebill.domain.entities.navigation.OrderItemTypeEnum
import com.example.splitthebill.domain.entities.orderitem.OrderItem
import com.example.splitthebill.presentation.customerdetails.CustomerBillDetailsFragmentArgs
import com.example.splitthebill.presentation.customerdetails.CustomerBillDetailsViewModel

class OrderItemDetails : Fragment() {
    private lateinit var binding: FragmentOrderItemDetailsBinding
    private val viewModel by viewModels<OrderItemDetailsViewModel>(factoryProducer = { OrderItemDetailsViewModel.Factory() })
    private val args: OrderItemDetailsArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrderItemDetailsBinding.inflate(layoutInflater)

        viewModel.validationErrorLiveData.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        }

        if (args.type == OrderItemTypeEnum.CREATE) {
            binding.confirmButton.text = "Confirmar criação"
            binding.confirmButton.setOnClickListener {
                val failed = viewModel.createOrUpdate(
                    name = binding.nameEditText.text.toString(),
                    price = binding.priceEditText.text.toString(),
                    quantity = binding.quantityEditText.text.toString(),
                    id = null,
                    customerId = args.customerId
                )
                if (!failed) {
                    findNavController().popBackStack()
                }
            }
        } else if (args.type == OrderItemTypeEnum.EDIT) {
            binding.confirmButton.text = "Confirmar alteração"
            binding.nameEditText.setText(args.orderItem?.name)
            binding.priceEditText.setText(args.orderItem?.price.toString())
            binding.quantityEditText.setText(args.orderItem?.quantity.toString())
            binding.confirmButton.setOnClickListener {
                val failed = viewModel.createOrUpdate(
                    id = args.orderItem?.id,
                    name = binding.nameEditText.text.toString(),
                    price = binding.priceEditText.text.toString(),
                    quantity = binding.quantityEditText.text.toString(),
                    customerId = args.customerId
                )
                if (!failed) {
                    findNavController().popBackStack()
                }
            }
        }

        return binding.root
    }

}