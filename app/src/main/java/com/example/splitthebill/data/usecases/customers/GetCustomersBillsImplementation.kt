package com.example.splitthebill.data.usecases.customers

import com.example.splitthebill.domain.entities.customers.CustomerBill
import com.example.splitthebill.domain.entities.customers.CustomerBillDetails
import com.example.splitthebill.domain.repositories.customers.GetCustomersBillsRepository
import com.example.splitthebill.domain.usecases.customers.GetCustomersBillsUseCase

class GetCustomersBillsImplementation(private val getCustomersBillsRepository: GetCustomersBillsRepository) :
    GetCustomersBillsUseCase {
    override suspend fun getCustomersBills(): List<CustomerBill> {
        return getCustomersBillsRepository.get()
    }
}