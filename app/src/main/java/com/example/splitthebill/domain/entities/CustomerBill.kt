package com.example.splitthebill.domain.entities

data class CustomerBill(
    val id: Number?,
    val customerName: String,
    val totalPrice: Number,
    val orderQuantity: Number
)
