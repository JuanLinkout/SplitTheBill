package com.example.splitthebill.domain.repositories.customers

data class CustomerRepository(
    val createCustomerBillRepository: CreateCustomerBillRepository,
    val updateCustomerBillRepository: UpdateCustomerBillRepository,
    val getCustomerDetailsRepository: GetCustomersDetailsRepository,
    val getCustomersBillsRepository: GetCustomersBillsRepository
)
