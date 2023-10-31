package com.example.splitthebill.domain.usecases.customers

import com.example.splitthebill.domain.entities.customers.CustomerBill
import com.example.splitthebill.domain.entities.customers.CustomerSplitResult

interface SplitCustomerBillsUseCase {
    suspend fun split(customers: List<CustomerBill>): List<CustomerSplitResult>
}