package com.example.splitthebill.data.usecases.customers

import com.example.splitthebill.domain.entities.customers.CustomerBillDetails
import com.example.splitthebill.domain.entities.orderitem.OrderItem
import com.example.splitthebill.domain.usecases.customers.GetCustomerBillDetailsUseCase

class GetCustomerBillDetailsImplementation: GetCustomerBillDetailsUseCase {
    override suspend fun getCustomerBillDetails(): CustomerBillDetails {
        return CustomerBillDetails(
            id = 1,
            customerName = "Juan Rossi",
            orderItems = listOf(OrderItem(id = 1, name = "Teste", price = 10.9, quantity = 2))
        )
    }
}