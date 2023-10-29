package com.example.splitthebill.domain.repositories.customers

import com.example.splitthebill.domain.entities.customers.CustomerDetailsFromRepository

interface GetCustomersDetailsRepository {
    suspend fun get(id: Number): CustomerDetailsFromRepository
}