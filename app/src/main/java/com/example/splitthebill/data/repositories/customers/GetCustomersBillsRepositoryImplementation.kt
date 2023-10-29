package com.example.splitthebill.data.repositories.customers

import com.example.splitthebill.domain.entities.customers.CustomerBill
import com.example.splitthebill.domain.entities.customers.CustomerBillDetails
import com.example.splitthebill.domain.repositories.customers.GetCustomersBillsRepository

class GetCustomersBillsRepositoryImplementation : GetCustomersBillsRepository {
    override suspend fun get(): List<CustomerBill> {
        return listOf(
            CustomerBill(customerName = "Juan Rossi", id = 1, totalPrice = 10.2, orderQuantity = 20)
        )
    }
}