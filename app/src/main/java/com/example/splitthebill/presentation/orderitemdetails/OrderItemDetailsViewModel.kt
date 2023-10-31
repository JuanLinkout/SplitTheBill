package com.example.splitthebill.presentation.orderitemdetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.splitthebill.domain.entities.orderitem.OrderItem
import com.example.splitthebill.domain.usecases.orders.CreateOrUpdateOrderItemUseCase
import com.example.splitthebill.presentation.MainActivity
import kotlinx.coroutines.launch

class OrderItemDetailsViewModel(private val createOrUpdateOrderItemUseCase: CreateOrUpdateOrderItemUseCase) :
    ViewModel() {

    private val _validationErrorLiveData = MutableLiveData<String>()
    val validationErrorLiveData: LiveData<String> = _validationErrorLiveData


    fun createOrUpdate(name: String, price: String, quantity: String, id: Number?, customerId: Long): Boolean {
        if (name.isEmpty() || price.isEmpty() || quantity.isEmpty()) {
            _validationErrorLiveData.value = "Preencha todos os campos"
            return true
        }

        viewModelScope.launch {
            createOrUpdateOrderItemUseCase.execute(
                OrderItem(
                    name = name, price = price.toDouble(), quantity = quantity.toLong(), id = id
                ), customerId
            )
        }
        return false
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