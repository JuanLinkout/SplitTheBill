package com.example.splitthebill.presentation.orderitemdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.splitthebill.domain.entities.orderitem.OrderItem
import kotlinx.coroutines.launch

class OrderItemDetailsViewModel : ViewModel() {

    fun createOrUpdate(orderItem: OrderItem) {
        viewModelScope.launch {

        }
    }

    companion object {
        fun Factory(): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return OrderItemDetailsViewModel() as T
                }
            }
        }
    }
}