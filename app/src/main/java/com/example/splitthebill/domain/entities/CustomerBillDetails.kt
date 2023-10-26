package com.example.splitthebill.domain.entities

data class CustomerBillDetails(
    val id: Number,
    val customerName: String,
    val orderItems: List<OrderItem>
)