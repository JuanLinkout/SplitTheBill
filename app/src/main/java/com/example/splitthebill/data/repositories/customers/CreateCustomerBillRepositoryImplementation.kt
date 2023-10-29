package com.example.splitthebill.data.repositories.customers

import com.example.splitthebill.domain.entities.customers.CustomerBillDetails
import com.example.splitthebill.domain.repositories.customers.CreateCustomerBillRepository

class CreateCustomerBillRepositoryImplementation: CreateCustomerBillRepository {
    override suspend fun create(customerBill: CustomerBillDetails) {
        TODO("Not yet implemented")
    }
}