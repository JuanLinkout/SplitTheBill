package com.example.splitthebill.domain.repositories.customers

import com.example.splitthebill.domain.entities.orderitem.OrderItem

interface DeleteAllCustomersBillsRepository {
    suspend fun deleteAll(): Unit
}