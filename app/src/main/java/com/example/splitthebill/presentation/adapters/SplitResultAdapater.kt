package com.example.splitthebill.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.splitthebill.databinding.CustomerBillItemBinding
import com.example.splitthebill.databinding.CustomerBillSplitItemBinding
import com.example.splitthebill.databinding.FragmentSplitResultBinding
import com.example.splitthebill.domain.entities.customers.CustomerBill
import com.example.splitthebill.domain.entities.customers.CustomerBillDetails
import com.example.splitthebill.domain.entities.customers.CustomerSplitResult
import com.example.splitthebill.domain.entities.navigation.CustomerBillTypeEnum
import com.example.splitthebill.presentation.customers.CustomersBillsFragmentDirections

class SplitResultAdapater(
    private val dataset: Array<CustomerSplitResult>,
    private val onClickListener: AdapterOnClickListener
) :
    RecyclerView.Adapter<SplitResultAdapater.CustomerBillItemViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SplitResultAdapater.CustomerBillItemViewHolder {
        val binding =
            CustomerBillSplitItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomerBillItemViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: SplitResultAdapater.CustomerBillItemViewHolder,
        position: Int
    ) {
        holder.bind(dataset[position], position)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    inner class CustomerBillItemViewHolder(
        private val binding: CustomerBillSplitItemBinding

    ) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(splitResult: CustomerSplitResult, position: Int) {
            binding.customerInitialText.text = splitResult.customerName[0].toString()
            binding.transactioValueText.text = "Total: R$ " + splitResult.valueAmount.toString()
            binding.customerNameText.text = splitResult.customerName

            if (splitResult.valueAmount.toDouble() < 0) {
                binding.transactionTypeText.text = "Deve pagar"
            } else {
                binding.transactionTypeText.text = "Deve receber"
            }

            binding.transactionTypeText.text
        }
    }
}