package com.example.splitthebill.domain.entities.customers

import androidx.room.Embedded

data class CustomerDetailsFromRepository(
    @Embedded
    val id: Number?,
    val customerName: String
)