package com.example.splitthebill.data.repositories

import com.example.splitthebill.domain.entities.CustomerBill
import com.example.splitthebill.domain.repositories.CreateCustomerBillRepository

class CreateCustomerBillRepositoryImplementation: CreateCustomerBillRepository {
    override suspend fun create(customerBill: CustomerBill) {
        TODO("Not yet implemented")
    }
}