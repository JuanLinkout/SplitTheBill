package com.example.splitthebill.domain.repositories.customers

import com.example.splitthebill.domain.entities.customers.CustomerBill

interface UpdateCustomerBillRepository {
    suspend fun update(customerBill: CustomerBill): Unit
}