package com.example.splitthebill.data.usecases.customers

import com.example.splitthebill.domain.entities.customers.CustomerBill
import com.example.splitthebill.domain.entities.customers.CustomerBillDetails
import com.example.splitthebill.domain.entities.customers.CustomerSplitResult
import com.example.splitthebill.domain.repositories.customers.CreateCustomerBillRepository
import com.example.splitthebill.domain.repositories.customers.UpdateCustomerBillRepository
import com.example.splitthebill.domain.usecases.customers.CreateOrUpdateCustomerBillUseCase
import com.example.splitthebill.domain.usecases.customers.SplitCustomerBillsUseCase

class SplitCustomerBillImplementation() : SplitCustomerBillsUseCase {
    override suspend fun split(customers: List<CustomerBill>): List<CustomerSplitResult> {
        val totalResult = customers.sumOf { it.totalPrice.toDouble() }
        val splitQuantity = customers.size
        val average = totalResult / splitQuantity
        val splitResults = customers.map {
            CustomerSplitResult(
                customerName = it.customerName,
                valueAmount = it.totalPrice.toDouble() - average,
                customerId = it.id!!
            )
        }
        return splitResults
    }
}