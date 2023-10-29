package com.example.splitthebill.domain.repositories.orders

import com.example.splitthebill.domain.entities.orderitem.OrderItem

interface GetCustomerOrdersByIdRepository {
    suspend fun getOrders(id: Number): List<OrderItem>
}