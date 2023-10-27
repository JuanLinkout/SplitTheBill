package com.example.splitthebill.presentation.orderitemdetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.splitthebill.R
import com.example.splitthebill.databinding.FragmentOrderItemDetailsBinding

class OrderItemDetails : Fragment() {
    private lateinit var binding: FragmentOrderItemDetailsBinding
    private lateinit var viewModel: OrderItemDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrderItemDetailsBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(OrderItemDetailsViewModel::class.java)
        return binding.root
    }

}