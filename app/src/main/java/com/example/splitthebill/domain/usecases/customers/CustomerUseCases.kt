package com.example.splitthebill.domain.usecases.customers

data class CustomerUseCases(
    val createOrUpdateCustomerBill: CreateOrUpdateCustomerBillUseCase,
    val getCustomerBillDetails: GetCustomerBillDetailsUseCase,
    val getCustomersBills: GetCustomersBillsUseCase
)