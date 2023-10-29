package com.example.splitthebill.presentation.customerdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.splitthebill.data.repositories.customers.CreateCustomerBillRepositoryImplementation
import com.example.splitthebill.data.repositories.customers.GetCustomerDetailsRepositoryImplementation
import com.example.splitthebill.data.repositories.customers.UpdateCustomerBillRepositoryImplementation
import com.example.splitthebill.data.repositories.orders.GetCustomerOrdersByIdRepositoryImplementation
import com.example.splitthebill.data.usecases.customers.CreateOrUpdateCustomerBillImplementantion
import com.example.splitthebill.data.usecases.customers.GetCustomerBillDetailsImplementation
import com.example.splitthebill.domain.entities.customers.CustomerBillDetails
import com.example.splitthebill.domain.usecases.customers.GetCustomerBillDetailsUseCase
import kotlinx.coroutines.launch

class CustomerBillDetailsViewModel(
    private val getCustomerBillDetails: GetCustomerBillDetailsUseCase,
    private val createOrUpdateCustomerBill: CreateOrUpdateCustomerBillImplementantion
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

    companion object {
        fun Factory(): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return CustomerBillDetailsViewModel(
                        GetCustomerBillDetailsImplementation(
                            getCustomerDetailsRepository = GetCustomerDetailsRepositoryImplementation(),
                            getCustomerOrdersByIdRepository = GetCustomerOrdersByIdRepositoryImplementation()
                        ),
                        CreateOrUpdateCustomerBillImplementantion(
                            createCustomerBillRepository = CreateCustomerBillRepositoryImplementation(),
                            updateCustomerBillRepository = UpdateCustomerBillRepositoryImplementation()
                        )
                    ) as T
                }
            }
        }
    }
}