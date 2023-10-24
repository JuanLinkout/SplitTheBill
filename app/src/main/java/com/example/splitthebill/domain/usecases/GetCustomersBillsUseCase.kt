package com.example.splitthebill.domain.usecases

import com.example.splitthebill.domain.entities.CustomersBills

interface GetCustomersBillsUseCase {
    suspend fun getCustomersBills(): List<CustomersBills>
}