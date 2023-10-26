package com.example.splitthebill.data.repositories

import com.example.splitthebill.domain.entities.CustomerBill
import com.example.splitthebill.domain.repositories.CreateCustomerBillRepository
import com.example.splitthebill.domain.repositories.GetCustomersBillsRepository

class GetCustomersBillsRepositoryImplementation : GetCustomersBillsRepository {
    override suspend fun get(): List<CustomerBill> {
        return listOf(
            CustomerBill(customerName = "Juan Rossi", id = 1, totalPrice = 10.2, orderQuantity = 20)
        )
    }
}