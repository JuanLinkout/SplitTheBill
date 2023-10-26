package com.example.splitthebill.data.usecases.customers

import com.example.splitthebill.domain.entities.customers.CustomerBill
import com.example.splitthebill.domain.repositories.customers.CreateCustomerBillRepository
import com.example.splitthebill.domain.repositories.customers.UpdateCustomerBillRepository
import com.example.splitthebill.domain.usecases.customers.CreateOrUpdateCustomerBillUseCase

class CreateOrUpdateCustomerBillImplementantion(
    private val createCustomerBillRepository: CreateCustomerBillRepository,
    private val updateCustomerBillRepository: UpdateCustomerBillRepository
) : CreateOrUpdateCustomerBillUseCase {
    override suspend fun execute(customerBill: CustomerBill) {
        if (customerBill.id == null) {
            createCustomerBillRepository.create(customerBill)
        } else {
            updateCustomerBillRepository.update(customerBill)
        }
    }
}