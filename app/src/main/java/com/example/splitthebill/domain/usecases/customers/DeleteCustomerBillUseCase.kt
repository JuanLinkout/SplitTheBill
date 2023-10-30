package com.example.splitthebill.domain.usecases.customers

interface DeleteCustomerBillUseCase {
    suspend fun delete(customerId: Number): Unit
}