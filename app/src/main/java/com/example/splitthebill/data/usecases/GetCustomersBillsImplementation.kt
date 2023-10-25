package com.example.splitthebill.data.usecases

import com.example.splitthebill.domain.entities.CustomerBill
import com.example.splitthebill.domain.usecases.GetCustomersBillsUseCase

class GetCustomersBillsImplementation : GetCustomersBillsUseCase {
    override suspend fun getCustomersBills(): List<CustomerBill> {
        return listOf(
            CustomerBill(customerName = "Juan Rossi", id = 1, totalPrice = 10.2, orderQuantity = 20)
        )
    }
}