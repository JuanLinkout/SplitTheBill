package com.example.splitthebill.presentation.customerdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.splitthebill.data.usecases.customers.GetCustomerBillDetailsImplementation
import com.example.splitthebill.domain.entities.customers.CustomerBillDetails
import com.example.splitthebill.domain.usecases.customers.GetCustomerBillDetailsUseCase
import kotlinx.coroutines.launch

class CustomerBillDetailsViewModel(private val getCustomerBillDetails: GetCustomerBillDetailsUseCase) :
    ViewModel() {
    private val _customerBillDetails = MutableLiveData<CustomerBillDetails>()
    val customerBillDetails: LiveData<CustomerBillDetails> = _customerBillDetails

    init {
        viewModelScope.launch {
            val response = getCustomerBillDetails.getCustomerBillDetails()
            _customerBillDetails.value = response
        }
    }

    companion object {
        fun Factory(): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return CustomerBillDetailsViewModel(GetCustomerBillDetailsImplementation()) as T
                }
            }
        }
    }
}