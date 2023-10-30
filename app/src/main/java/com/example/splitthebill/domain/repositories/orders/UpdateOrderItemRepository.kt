package com.example.splitthebill.domain.repositories.orders

import com.example.splitthebill.domain.entities.orderitem.OrderItem

interface UpdateOrderItemRepository {
    suspend fun update(orderItem: OrderItem, customerId: Number): Unit
}