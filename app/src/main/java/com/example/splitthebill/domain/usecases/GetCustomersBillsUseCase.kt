package com.example.splitthebill.domain.usecases

import com.example.splitthebill.domain.entities.CustomerBill

interface GetCustomersBillsUseCase {
    suspend fun getCustomersBills(): List<CustomerBill>
}