package com.example.splitthebill.domain.entities

data class CustomerBill(
    val customerName: String,
    val id: Number,
    val totalPrice: Number,
    val orderQuantity: Number
)
