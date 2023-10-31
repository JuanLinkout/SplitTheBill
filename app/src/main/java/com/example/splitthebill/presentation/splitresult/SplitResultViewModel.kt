package com.example.splitthebill.presentation.splitresult

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.splitthebill.domain.usecases.customers.DeleteAllCustomersBillsUseCase
import com.example.splitthebill.presentation.MainActivity
import kotlinx.coroutines.launch

class SplitResultViewModel(private val deleteAllCustomersBillsUseCase: DeleteAllCustomersBillsUseCase) :
    ViewModel() {

    fun clearCustomerBills(customerIds: List<Number>, onComplete: () -> Unit) {
        viewModelScope.launch {
            deleteAllCustomersBillsUseCase.deleteAll(customerIds)
            onComplete()
        }
    }

    companion object {
        fun Factory(): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return SplitResultViewModel(MainActivity.appModule.customerUseCases.deleteAllCustomersBillsUseCase) as T
                }
            }
        }
    }
}