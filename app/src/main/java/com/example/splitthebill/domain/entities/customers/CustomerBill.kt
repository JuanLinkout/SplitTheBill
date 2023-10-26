package com.example.splitthebill.domain.entities.customers

data class CustomerBill(
    val id: Number?,
    val customerName: String,
    val totalPrice: Number,
    val orderQuantity: Number
)
