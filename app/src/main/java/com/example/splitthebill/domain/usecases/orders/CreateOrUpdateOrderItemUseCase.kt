package com.example.splitthebill.domain.usecases.orders

import com.example.splitthebill.domain.entities.orderitem.OrderItem

interface CreateOrUpdateOrderItemUseCase {
    suspend fun execute(orderItem: OrderItem, customerId: Number): Unit
}