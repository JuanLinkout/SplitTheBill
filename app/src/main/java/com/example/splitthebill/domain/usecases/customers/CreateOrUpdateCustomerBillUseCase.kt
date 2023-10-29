package com.example.splitthebill.domain.usecases.customers

import com.example.splitthebill.domain.entities.customers.CustomerBillDetails

interface CreateOrUpdateCustomerBillUseCase {
    suspend fun execute(customerBill: CustomerBillDetails): Unit
}