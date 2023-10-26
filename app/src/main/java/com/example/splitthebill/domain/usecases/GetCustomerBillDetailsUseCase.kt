package com.example.splitthebill.domain.usecases

import com.example.splitthebill.domain.entities.CustomerBill
import com.example.splitthebill.domain.entities.CustomerBillDetails

interface GetCustomerBillDetailsUseCase {
    suspend fun getCustomerBillDetails(): CustomerBillDetails
}