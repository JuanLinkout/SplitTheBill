package com.example.splitthebill.data.usecases.customers

import com.example.splitthebill.domain.repositories.customers.DeleteAllCustomersBillsRepository
import com.example.splitthebill.domain.repositories.orders.DeleteAllOrderItemsRepository
import com.example.splitthebill.domain.usecases.customers.DeleteAllCustomersBillsUseCase

class DeleteAllCustomersBillsImplementation(
    private val deleteAllCustomersBillsRepository: DeleteAllCustomersBillsRepository,
    private val deleteAllOrderItemsRepository: DeleteAllOrderItemsRepository
) : DeleteAllCustomersBillsUseCase {
    override suspend fun deleteAll(customerIds: List<Number>) {
        customerIds.forEach { deleteAllOrderItemsRepository.deleteAll(it) }
        deleteAllCustomersBillsRepository.deleteAll()
    }
}