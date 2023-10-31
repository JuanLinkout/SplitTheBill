package com.example.splitthebill.domain.entities.customers

import androidx.room.Embedded
import com.example.splitthebill.domain.entities.orderitem.OrderItem

data class CustomerBill(
    @Embedded
    val id: Number?,
    val customerName: String,
    @Embedded
    val totalPrice: Number,
    @Embedded
    val orderQuantity: Number,
    val orders: List<OrderItem>
)
