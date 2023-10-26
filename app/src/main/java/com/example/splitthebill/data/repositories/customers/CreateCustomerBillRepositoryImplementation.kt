package com.example.splitthebill.data.repositories.customers

import com.example.splitthebill.domain.entities.customers.CustomerBill
import com.example.splitthebill.domain.repositories.customers.CreateCustomerBillRepository

class CreateCustomerBillRepositoryImplementation: CreateCustomerBillRepository {
    override suspend fun create(customerBill: CustomerBill) {
        TODO("Not yet implemented")
    }
}