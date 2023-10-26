package com.example.splitthebill.domain.usecases.customers

import com.example.splitthebill.domain.entities.customers.CustomerBill

interface GetCustomersBillsUseCase {
    suspend fun getCustomersBills(): List<CustomerBill>
}