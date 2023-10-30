package com.example.splitthebill.domain.repositories.orders

import com.example.splitthebill.domain.entities.orderitem.OrderItem

interface DeleteOrderItemRepository {
    suspend fun delete(orderItemId: Number): Unit
}