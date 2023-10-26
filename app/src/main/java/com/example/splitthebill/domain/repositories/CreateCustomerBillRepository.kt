package com.example.splitthebill.domain.repositories

import com.example.splitthebill.domain.entities.CustomerBill

interface CreateCustomerBillRepository {
    suspend fun create(customerBill: CustomerBill): Unit
}