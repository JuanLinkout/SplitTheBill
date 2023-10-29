package com.example.splitthebill.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.splitthebill.databinding.OrderItemBinding
import com.example.splitthebill.domain.entities.orderitem.OrderItem

class OrderItemAdapater(private val dataset: Array<OrderItem>) :
    RecyclerView.Adapter<OrderItemAdapater.OrderItemViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OrderItemAdapater.OrderItemViewHolder {
        val binding =
            OrderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderItemViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: OrderItemAdapater.OrderItemViewHolder,
        position: Int
    ) {
        holder.bind(dataset[position])
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    inner class OrderItemViewHolder(private val binding: OrderItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(orderItem: OrderItem) {
            val totalPrice = orderItem.price.toFloat() * orderItem.quantity.toFloat()

            binding.orderNameText.text = orderItem.name
            binding.orderTotalPriceText.text =
                "Total: R$ " + totalPrice.toString()
            binding.orderQuantityText.text =
                orderItem.quantity.toString() + "x"
//            binding.root.setOnClickListener {
//                val action =
//                    CustomersBillsFragmentDirections.actionCustomersBillsFragmentToCustomerBillDetailsFragment(
//                        customerBill.id!!.toInt()
//                    )
//                binding.root.findNavController().navigate(action)
//            }
        }
    }
}