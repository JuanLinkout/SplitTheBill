package com.example.splitthebill.data.repositories.orders

import com.example.splitthebill.domain.entities.customers.CustomerBill
import com.example.splitthebill.domain.entities.orderitem.OrderItem
import com.example.splitthebill.domain.repositories.orders.GetCustomerOrdersByIdRepository

class GetCustomerOrdersByIdRepositoryImplementation : GetCustomerOrdersByIdRepository {
    override suspend fun getOrders(id: Number): List<OrderItem> {
        return listOf(OrderItem(id = 1, name = "Teste", price = 10.9, quantity = 2))
    }
}