package com.example.splitthebill.presentation.customerdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.splitthebill.data.usecases.GetCustomerBillDetailsImplementation
import com.example.splitthebill.data.usecases.GetCustomersBillsImplementation
import com.example.splitthebill.domain.entities.CustomerBill
import com.example.splitthebill.domain.entities.CustomerBillDetails
import com.example.splitthebill.domain.usecases.GetCustomerBillDetailsUseCase
import com.example.splitthebill.domain.usecases.GetCustomersBillsUseCase
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