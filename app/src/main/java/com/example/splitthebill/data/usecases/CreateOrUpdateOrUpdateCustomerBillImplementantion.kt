package com.example.splitthebill.data.usecases

import com.example.splitthebill.domain.entities.CustomerBill
import com.example.splitthebill.domain.repositories.CreateCustomerBillRepository
import com.example.splitthebill.domain.repositories.UpdateCustomerBillRepository
import com.example.splitthebill.domain.usecases.CreateOrUpdateCustomerBillUseCase

class CreateOrUpdateOrUpdateCustomerBillImplementantion(
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