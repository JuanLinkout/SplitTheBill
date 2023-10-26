package com.example.splitthebill.domain.usecases

import com.example.splitthebill.domain.entities.CustomerBill

interface CreateOrUpdateCustomerBillUseCase {
    suspend fun execute(customerBill: CustomerBill): Unit
}