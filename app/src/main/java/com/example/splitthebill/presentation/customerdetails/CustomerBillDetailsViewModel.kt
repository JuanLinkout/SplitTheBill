package com.example.splitthebill.presentation.customerdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.splitthebill.domain.entities.customers.CustomerBillDetails
import com.example.splitthebill.domain.usecases.customers.CreateOrUpdateCustomerBillUseCase
import com.example.splitthebill.domain.usecases.customers.GetCustomerBillDetailsUseCase
import com.example.splitthebill.domain.usecases.orders.DeleteOrderItemUseCase
import com.example.splitthebill.presentation.MainActivity
import kotlinx.coroutines.launch

class CustomerBillDetailsViewModel(
    private val getCustomerBillDetails: GetCustomerBillDetailsUseCase,
    private val createOrUpdateCustomerBill: CreateOrUpdateCustomerBillUseCase,
    private val deleteOrderItemUseCase: DeleteOrderItemUseCase
) :
    ViewModel() {
    private val _customerBillDetails = MutableLiveData<CustomerBillDetails>()
    val customerBillDetails: LiveData<CustomerBillDetails> = _customerBillDetails

    fun fetchDetails(id: Number) {
        viewModelScope.launch {
            val response = getCustomerBillDetails.getCustomerBillDetails(id)
            _customerBillDetails.value = response
        }
    }

    fun createOrUpdate(customerBill: CustomerBillDetails) {
        viewModelScope.launch {
            createOrUpdateCustomerBill.execute(customerBill)
        }
    }

    fun delete(orderItemId: Number, customerId: Number) {
        viewModelScope.launch {
            deleteOrderItemUseCase.delete(orderItemId)
            fetchDetails(customerId)
        }
    }

    companion object {
        fun Factory(): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return CustomerBillDetailsViewModel(
                        MainActivity.appModule.customerUseCases.getCustomerBillDetails,
                        MainActivity.appModule.customerUseCases.createOrUpdateCustomerBill,
                        MainActivity.appModule.ordersUseCases.deleteOrderItemUseCase
                    ) as T
                }
            }
        }
    }
}