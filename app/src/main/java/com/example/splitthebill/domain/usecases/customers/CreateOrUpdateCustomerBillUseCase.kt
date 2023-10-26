package com.example.splitthebill.domain.usecases.customers

import com.example.splitthebill.domain.entities.customers.CustomerBill

interface CreateOrUpdateCustomerBillUseCase {
    suspend fun execute(customerBill: CustomerBill): Unit
}