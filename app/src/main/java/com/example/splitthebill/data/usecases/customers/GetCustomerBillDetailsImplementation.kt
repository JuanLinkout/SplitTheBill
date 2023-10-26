package com.example.splitthebill.data.usecases

import com.example.splitthebill.domain.entities.CustomerBillDetails
import com.example.splitthebill.domain.entities.OrderItem
import com.example.splitthebill.domain.usecases.GetCustomerBillDetailsUseCase

class GetCustomerBillDetailsImplementation: GetCustomerBillDetailsUseCase {
    override suspend fun getCustomerBillDetails(): CustomerBillDetails {
        return CustomerBillDetails(
            id = 1,
            customerName = "Juan Rossi",
            orderItems = listOf(OrderItem(id = 1, name = "Teste", price = 10.9, quantity = 2))
        )
    }
}