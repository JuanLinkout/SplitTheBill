package com.example.splitthebill.data.repositories.customers

import com.example.splitthebill.domain.entities.customers.CustomerBill
import com.example.splitthebill.domain.entities.customers.CustomerBillDetails
import com.example.splitthebill.domain.entities.customers.CustomerDetailsFromRepository
import com.example.splitthebill.domain.repositories.customers.GetCustomersBillsRepository
import com.example.splitthebill.domain.repositories.customers.GetCustomersDetailsRepository

class GetCustomerDetailsRepositoryImplementation : GetCustomersDetailsRepository {
    override suspend fun get(id: Number): CustomerDetailsFromRepository {
        return CustomerDetailsFromRepository(customerName = "Juan Rossi", id = 1)
    }
}