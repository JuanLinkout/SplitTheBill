package com.example.splitthebill.domain.entities.orderitem

import java.io.Serializable

data class OrderItem(
    val id: Number?,
    val name: String,
    val price: Number,
    val quantity: Number
) : Serializable
