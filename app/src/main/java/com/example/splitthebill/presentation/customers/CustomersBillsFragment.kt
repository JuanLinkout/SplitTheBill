package com.example.splitthebill.presentation.customers

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager.OnAdapterChangeListener
import com.example.splitthebill.databinding.FragmentCustomersBillsBinding
import com.example.splitthebill.domain.entities.customers.CustomerBill
import com.example.splitthebill.domain.entities.navigation.CustomerBillTypeEnum
import com.example.splitthebill.presentation.adapters.AdapterOnClickListener
import com.example.splitthebill.presentation.adapters.CustomerBillAdapter
import com.example.splitthebill.presentation.utils.StatusBarUtil

class CustomersBillsFragment : Fragment() {
    private lateinit var binding: FragmentCustomersBillsBinding
    private val viewModel by viewModels<CustomersBillsViewModel>(factoryProducer = { CustomersBillsViewModel.Factory() })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCustomersBillsBinding.inflate(layoutInflater)

        val observer = Observer<List<CustomerBill>> {
            binding.customerBillRecyclerView.adapter = CustomerBillAdapter(it.toTypedArray(), AdapaterCallback())
            binding.addCustomerButton.setOnClickListener {
                val action =
                    CustomersBillsFragmentDirections.actionCustomersBillsFragmentToCustomerBillDetailsFragment(
                        0,
                        CustomerBillTypeEnum.CREATE
                    )
                binding.root.findNavController().navigate(action)
            }
        }
        viewModel.customerBills.observe(viewLifecycleOwner, observer)

        val statusBarHeight = StatusBarUtil.getStatusBarHeight(binding.root.context)
        binding.customerBillRecyclerView.setPadding(16, 16 + statusBarHeight, 16, 16)
        binding.customerBillRecyclerView.layoutManager = LinearLayoutManager(binding.root.context)

        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        viewModel.fetchData()
        binding.customerBillRecyclerView.adapter?.notifyDataSetChanged()
    }

    inner class AdapaterCallback: AdapterOnClickListener {
        override fun onTileContactClick(position: Int) {
            val list = viewModel.customerBills.value
            val customerBill = list?.find { it.id == list[position].id } ?: return
            val action =
                CustomersBillsFragmentDirections.actionCustomersBillsFragmentToCustomerBillDetailsFragment(
                    customerBill.id!!.toInt(),
                    CustomerBillTypeEnum.EDIT
                )
            binding.root.findNavController().navigate(action)
        }

        @SuppressLint("NotifyDataSetChanged")
        override fun onRemoveClick(position: Int) {
            val list = viewModel.customerBills.value
            val customerBill = list?.find { it.id == list[position].id } ?: return
            customerBill.id?.let {
                viewModel.deleteCustomerBill(it.toInt())
                binding.customerBillRecyclerView.adapter?.notifyDataSetChanged()
            }
        }
    }
}