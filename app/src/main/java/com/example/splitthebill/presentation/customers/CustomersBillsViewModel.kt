package com.example.splitthebill.presentation.customers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.splitthebill.data.usecases.GetCustomersBillsImplementation
import com.example.splitthebill.domain.entities.CustomerBill
import com.example.splitthebill.domain.usecases.GetCustomersBillsUseCase
import kotlinx.coroutines.launch

class CustomersBillsViewModel(private val getCustomersBillsUseCase: GetCustomersBillsUseCase): ViewModel() {
    private val _customerBills = MutableLiveData<List<CustomersBills>>()
    val customerBills: LiveData<List<CustomerBill>> = _customerBills

    init {
        viewModelScope.launch {
            val customerBillsList = getCustomersBillsUseCase.getCustomersBills()
            _customerBills.value = customerBillsList
        }
    }

    companion object {
        fun Factory(): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return CustomersBillsViewModel(GetCustomersBillsImplementation()) as T
                }
            }
        }
    }
}