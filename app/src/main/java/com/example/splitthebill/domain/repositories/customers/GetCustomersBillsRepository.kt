package com.example.splitthebill.domain.repositories.customers

import com.example.splitthebill.domain.entities.customers.CustomerDetailsFromRepository

interface GetCustomersBillsRepository {
    suspend fun get(): List<CustomerDetailsFromRepository>
}