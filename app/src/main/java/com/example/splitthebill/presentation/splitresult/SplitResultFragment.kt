package com.example.splitthebill.presentation.splitresult

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.splitthebill.databinding.FragmentSplitResultBinding
import com.example.splitthebill.domain.entities.navigation.CustomerBillTypeEnum
import com.example.splitthebill.presentation.adapters.AdapterOnClickListener
import com.example.splitthebill.presentation.adapters.SplitResultAdapater
import com.example.splitthebill.presentation.customers.CustomersBillsFragmentDirections
import com.example.splitthebill.presentation.customers.CustomersBillsViewModel
import kotlinx.coroutines.launch

class SplitResultFragment : Fragment() {
    private lateinit var binding: FragmentSplitResultBinding
    private val args: SplitResultFragmentArgs by navArgs()
    private val viewModel by viewModels<SplitResultViewModel>(factoryProducer = { SplitResultViewModel.Factory() })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplitResultBinding.inflate(layoutInflater)

        binding.splitResultsRecyclerView.adapter =
            SplitResultAdapater(args.splittedBills, AdapterCallback())
        binding.splitResultsRecyclerView.layoutManager = LinearLayoutManager(binding.root.context)
        binding.confirmButton.setOnClickListener {
            viewModel.clearCustomerBills(args.splittedBills.map { it.customerId }) {
                findNavController().popBackStack()
            }
        }

        return binding.root
    }

    inner class AdapterCallback : AdapterOnClickListener {
        override fun onTileContactClick(position: Int) {
            return
        }

        override fun onRemoveClick(position: Int) {
            return
        }
    }
}