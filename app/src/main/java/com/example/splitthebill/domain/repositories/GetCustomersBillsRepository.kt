package com.example.splitthebill.domain.repositories

import com.example.splitthebill.domain.entities.CustomerBill

interface GetCustomersBillsRepository {
    suspend fun get(): List<CustomerBill>
}