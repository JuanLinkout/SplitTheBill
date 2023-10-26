package com.example.splitthebill.domain.repositories.customers

import com.example.splitthebill.domain.entities.customers.CustomerBill

interface CreateCustomerBillRepository {
    suspend fun create(customerBill: CustomerBill): Unit
}