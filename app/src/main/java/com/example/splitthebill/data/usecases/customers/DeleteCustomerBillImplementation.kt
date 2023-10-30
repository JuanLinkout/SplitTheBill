package com.example.splitthebill.data.usecases.customers

import com.example.splitthebill.domain.entities.customers.CustomerBillDetails
import com.example.splitthebill.domain.repositories.customers.DeleteCustomerBillRepository
import com.example.splitthebill.domain.repositories.customers.GetCustomersDetailsRepository
import com.example.splitthebill.domain.repositories.orders.DeleteAllOrderItemsRepository
import com.example.splitthebill.domain.repositories.orders.GetCustomerOrdersByIdRepository
import com.example.splitthebill.domain.usecases.customers.DeleteCustomerBillUseCase
import com.example.splitthebill.domain.usecases.customers.GetCustomerBillDetailsUseCase

class DeleteCustomerBillImplementation(
    private val deleteCustomerBillRepository: DeleteCustomerBillRepository,
    private val deleteAllOrderItemsRepository: DeleteAllOrderItemsRepository
) : DeleteCustomerBillUseCase {
    override suspend fun delete(customerId: Number) {
        deleteAllOrderItemsRepository.deleteAll(customerId)
        deleteCustomerBillRepository.delete(customerId)
    }
}