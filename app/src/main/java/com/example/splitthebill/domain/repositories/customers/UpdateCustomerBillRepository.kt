package com.example.splitthebill.domain.repositories.customers

import com.example.splitthebill.domain.entities.customers.CustomerBillDetails

interface UpdateCustomerBillRepository {
    suspend fun update(customerBill: CustomerBillDetails): Unit
}