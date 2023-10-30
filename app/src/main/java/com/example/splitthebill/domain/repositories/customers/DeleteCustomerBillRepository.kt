package com.example.splitthebill.domain.repositories.customers

interface DeleteCustomerBillRepository {
    suspend fun delete(customerBillId: Number): Unit
}