package com.example.splitthebill.domain.repositories.customers

import com.example.splitthebill.domain.entities.customers.CustomerBillDetails

interface CreateCustomerBillRepository {
    suspend fun create(customerBill: CustomerBillDetails): Unit
}