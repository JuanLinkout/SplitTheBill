package com.example.splitthebill.domain.usecases.orders

import com.example.splitthebill.domain.entities.orderitem.OrderItem

interface DeleteOrderItemUseCase {
    suspend fun delete(orderItemId: Number): Unit
}