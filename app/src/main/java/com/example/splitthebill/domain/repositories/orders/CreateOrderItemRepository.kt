package com.example.splitthebill.domain.repositories.orders

import com.example.splitthebill.domain.entities.orderitem.OrderItem

interface CreateOrderItemRepository {
    suspend fun create(orderItem: OrderItem, customerId: Number): Unit
}