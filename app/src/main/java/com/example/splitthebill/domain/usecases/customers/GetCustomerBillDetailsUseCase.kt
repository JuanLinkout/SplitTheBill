package com.example.splitthebill.domain.usecases.customers

import com.example.splitthebill.domain.entities.customers.CustomerBillDetails

interface GetCustomerBillDetailsUseCase {
    suspend fun getCustomerBillDetails(): CustomerBillDetails
}