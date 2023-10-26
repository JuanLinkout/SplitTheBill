package com.example.splitthebill.domain.entities.customers

import com.example.splitthebill.domain.entities.orderitem.OrderItem

data class CustomerBillDetails(
    val id: Number,
    val customerName: String,
    val orderItems: List<OrderItem>
)