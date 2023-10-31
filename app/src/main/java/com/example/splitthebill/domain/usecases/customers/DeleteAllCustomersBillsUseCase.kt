package com.example.splitthebill.domain.usecases.customers

interface DeleteAllCustomersBillsUseCase {
    suspend fun deleteAll(customerIds: List<Number>): Unit
}