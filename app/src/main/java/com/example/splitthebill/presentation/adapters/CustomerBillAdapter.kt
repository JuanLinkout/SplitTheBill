package com.example.splitthebill.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.splitthebill.databinding.CustomerBillItemBinding
import com.example.splitthebill.domain.entities.customers.CustomerBill
import com.example.splitthebill.domain.entities.customers.CustomerBillDetails
import com.example.splitthebill.domain.entities.navigation.CustomerBillTypeEnum
import com.example.splitthebill.presentation.customers.CustomersBillsFragmentDirections

class CustomerBillAdapter(
    private val dataset: Array<CustomerBill>,
    private val onClickListener: AdapterOnClickListener
) :
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
        holder.bind(dataset[position], position)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    inner class CustomerBillItemViewHolder(
        private val binding: CustomerBillItemBinding

    ) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(customerBill: CustomerBill, position: Int) {
            binding.customerInitialText.text = customerBill.customerName[0].toString()
            binding.customerTotalPrice.text = "Total: R$ " + customerBill.totalPrice.toString()
            binding.customerNameText.text = customerBill.customerName

            var orderItemNames = ""
            customerBill.orders.forEachIndexed { index, orderItem ->
                orderItemNames += orderItem.name
                if (index != customerBill.orders.size - 1) orderItemNames += ", "
            }
            if (orderItemNames.isEmpty()) { binding.orderItemsNamesTextView.visibility = View.GONE }
            binding.orderItemsNamesTextView.text = orderItemNames
            binding.customerOrdersQuantityTxt.text =
                "Pedidos " + customerBill.orderQuantity.toString()
            binding.deleteButton.setOnClickListener { onClickListener.onRemoveClick(position) }
            binding.root.setOnClickListener { onClickListener.onTileContactClick(position) }
        }
    }
}