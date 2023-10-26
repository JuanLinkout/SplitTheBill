package com.example.splitthebill.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.splitthebill.databinding.CustomerBillItemBinding
import com.example.splitthebill.domain.entities.customers.CustomerBill

class CustomerBillAdapter(private val dataset: Array<CustomerBill>): RecyclerView.Adapter<CustomerBillAdapter.CustomerBillItemViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomerBillAdapter.CustomerBillItemViewHolder {
        val binding = CustomerBillItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

    inner class CustomerBillItemViewHolder(private val binding: CustomerBillItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(customerBill: CustomerBill) {
            binding.customerInitialText.text = customerBill.customerName[0].toString()
            binding.customerTotalPrice.text = "Total: R$ " + customerBill.totalPrice.toString()
            binding.customerNameText.text = customerBill.customerName
            binding.customerOrdersQuantityTxt.text = "Pedidos " + customerBill.orderQuantity.toString()
        }
    }
}