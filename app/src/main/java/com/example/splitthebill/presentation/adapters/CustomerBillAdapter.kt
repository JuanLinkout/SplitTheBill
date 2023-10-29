package com.example.splitthebill.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.splitthebill.databinding.CustomerBillItemBinding
import com.example.splitthebill.domain.entities.customers.CustomerBill
import com.example.splitthebill.domain.entities.customers.CustomerBillDetails
import com.example.splitthebill.domain.entities.navigation.CustomerBillTypeEnum
import com.example.splitthebill.presentation.customers.CustomersBillsFragmentDirections

class CustomerBillAdapter(private val dataset: Array<CustomerBill>) :
    RecyclerView.Adapter<CustomerBillAdapter.CustomerBillItemViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomerBillAdapter.CustomerBillItemViewHolder {
        val binding =
            CustomerBillItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomerBillItemViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: CustomerBillAdapter.CustomerBillItemViewHolder,
        position: Int
    ) {
        holder.bind(dataset[position])
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    inner class CustomerBillItemViewHolder(private val binding: CustomerBillItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(customerBill: CustomerBill) {
            binding.customerInitialText.text = customerBill.customerName[0].toString()
            binding.customerTotalPrice.text = "Total: R$ " + customerBill.totalPrice.toString()
            binding.customerNameText.text = customerBill.customerName
            binding.customerOrdersQuantityTxt.text =
                "Pedidos " + customerBill.orderQuantity.toString()
            binding.root.setOnClickListener {
                val action =
                    CustomersBillsFragmentDirections.actionCustomersBillsFragmentToCustomerBillDetailsFragment(
                        customerBill.id!!.toInt(),
                        CustomerBillTypeEnum.EDIT
                    )
                binding.root.findNavController().navigate(action)
            }
        }
    }
}