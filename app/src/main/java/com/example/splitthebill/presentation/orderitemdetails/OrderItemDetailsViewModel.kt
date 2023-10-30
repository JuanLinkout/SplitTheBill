package com.example.splitthebill.presentation.orderitemdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.splitthebill.domain.entities.orderitem.OrderItem
import com.example.splitthebill.domain.usecases.orders.CreateOrUpdateOrderItemUseCase
import com.example.splitthebill.presentation.MainActivity
import kotlinx.coroutines.launch

class OrderItemDetailsViewModel(private val createOrUpdateOrderItemUseCase: CreateOrUpdateOrderItemUseCase) :
    ViewModel() {

    fun createOrUpdate(orderItem: OrderItem, customerId: Long) {
        viewModelScope.launch {
            createOrUpdateOrderItemUseCase.execute(orderItem, customerId)
        }
    }

    companion object {
        fun Factory(): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return OrderItemDetailsViewModel(
                        MainActivity.appModule.ordersUseCases.createOrUpdateOrderItemUseCase
                    ) as T
                }
            }
        }
    }
}