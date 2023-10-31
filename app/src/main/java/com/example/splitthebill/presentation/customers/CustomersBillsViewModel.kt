package com.example.splitthebill.presentation.customers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.splitthebill.data.usecases.customers.SplitCustomerBillImplementation
import com.example.splitthebill.domain.entities.customers.CustomerBill
import com.example.splitthebill.domain.entities.customers.CustomerSplitResult
import com.example.splitthebill.domain.usecases.customers.DeleteCustomerBillUseCase
import com.example.splitthebill.domain.usecases.customers.GetCustomersBillsUseCase
import com.example.splitthebill.domain.usecases.customers.SplitCustomerBillsUseCase
import com.example.splitthebill.presentation.MainActivity
import kotlinx.coroutines.launch

class CustomersBillsViewModel(
    private val getCustomersBillsUseCase: GetCustomersBillsUseCase,
    private val deleteCustomerBillUseCase: DeleteCustomerBillUseCase,
    private val splitCustomerBillImplementation: SplitCustomerBillsUseCase
) :
    ViewModel() {
    private val _customerBills = MutableLiveData<List<CustomerBill>>()
    val customerBills: LiveData<List<CustomerBill>> = _customerBills

    private val _splittedBills = MutableLiveData<List<CustomerSplitResult>>()
    val splittedBills: LiveData<List<CustomerSplitResult>> = _splittedBills

    fun fetchData() {
        viewModelScope.launch {
            val customerBillsList = getCustomersBillsUseCase.getCustomersBills()
            _customerBills.value = customerBillsList
        }
    }

    fun clearSplittedBills() {
        _splittedBills.value = emptyList()
    }

    fun splitBills() {
        viewModelScope.launch {
            _customerBills.value?.let {
                _splittedBills.value = splitCustomerBillImplementation.split(it)
            }
        }
    }

    fun deleteCustomerBill(customerId: Number) {
        viewModelScope.launch {
            deleteCustomerBillUseCase.delete(customerId)
            fetchData()
        }
    }

    init {
        fetchData()
    }

    companion object {
        fun Factory(): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return CustomersBillsViewModel(
                        MainActivity.appModule.customerUseCases.getCustomersBills,
                        MainActivity.appModule.customerUseCases.deleteCustomerBillUseCase,
                        MainActivity.appModule.customerUseCases.splitCustomerBillsUseCase
                    ) as T
                }
            }
        }
    }
}