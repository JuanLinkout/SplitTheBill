package com.example.splitthebill.data.usecases

import com.example.splitthebill.domain.entities.CustomersBills
import com.example.splitthebill.domain.usecases.GetCustomersBillsUseCase

class GetCustomersBillsImplementation : GetCustomersBillsUseCase {
    override suspend fun getCustomersBills(): List<CustomersBills> {
        return emptyList()
    }
}