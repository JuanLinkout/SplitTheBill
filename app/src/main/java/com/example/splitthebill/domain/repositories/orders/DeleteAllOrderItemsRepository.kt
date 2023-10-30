package com.example.splitthebill.domain.repositories.orders

import com.example.splitthebill.domain.entities.orderitem.OrderItem

interface DeleteAllOrderItemsRepository {
    suspend fun deleteAll(customerId: Number): Unit
}