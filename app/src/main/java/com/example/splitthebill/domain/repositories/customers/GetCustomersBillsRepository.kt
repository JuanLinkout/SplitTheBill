package com.example.splitthebill.domain.repositories.customers

import com.example.splitthebill.domain.entities.customers.CustomerBill

interface GetCustomersBillsRepository {
    suspend fun get(): List<CustomerBill>
}