package com.example.splitthebill.domain.repositories

import com.example.splitthebill.domain.entities.CustomerBill

interface UpdateCustomerBillRepository {
    suspend fun update(customerBill: CustomerBill): Unit
}