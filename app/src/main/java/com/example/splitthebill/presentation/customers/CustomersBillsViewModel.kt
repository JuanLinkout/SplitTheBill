package com.example.splitthebill.presentation.customers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.splitthebill.domain.entities.customers.CustomerBill
import com.example.splitthebill.domain.usecases.customers.GetCustomersBillsUseCase
import com.example.splitthebill.presentation.MainActivity
import kotlinx.coroutines.launch

class CustomersBillsViewModel(private val getCustomersBillsUseCase: GetCustomersBillsUseCase) :
    ViewModel() {
    private val _customerBills = MutableLiveData<List<CustomerBill>>()
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
                    return CustomersBillsViewModel(
                        MainActivity.appModule.customerUseCases.getCustomersBills
                    ) as T
                }
            }
        }
    }
}