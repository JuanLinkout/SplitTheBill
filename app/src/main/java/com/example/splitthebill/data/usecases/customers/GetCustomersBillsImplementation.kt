package com.example.splitthebill.data.usecases

import com.example.splitthebill.domain.entities.CustomerBill
import com.example.splitthebill.domain.repositories.GetCustomersBillsRepository
import com.example.splitthebill.domain.usecases.GetCustomersBillsUseCase

class GetCustomersBillsImplementation(private val getCustomersBillsRepository: GetCustomersBillsRepository) :
    GetCustomersBillsUseCase {
    override suspend fun getCustomersBills(): List<CustomerBill> {
        return getCustomersBillsRepository.get()
    }
}