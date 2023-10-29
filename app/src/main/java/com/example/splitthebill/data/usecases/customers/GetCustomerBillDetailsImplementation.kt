package com.example.splitthebill.data.usecases.customers

import com.example.splitthebill.data.repositories.customers.GetCustomerDetailsRepositoryImplementation
import com.example.splitthebill.domain.entities.customers.CustomerBillDetails
import com.example.splitthebill.domain.entities.orderitem.OrderItem
import com.example.splitthebill.domain.repositories.customers.GetCustomersDetailsRepository
import com.example.splitthebill.domain.repositories.orders.GetCustomerOrdersByIdRepository
import com.example.splitthebill.domain.usecases.customers.GetCustomerBillDetailsUseCase

class GetCustomerBillDetailsImplementation(
    private val getCustomerOrdersByIdRepository: GetCustomerOrdersByIdRepository,
    private val getCustomerDetailsRepository: GetCustomersDetailsRepository
) : GetCustomerBillDetailsUseCase {
    override suspend fun getCustomerBillDetails(id: Number): CustomerBillDetails {
        val ordersList = getCustomerOrdersByIdRepository.getOrders(id)
        val customerDetails = getCustomerDetailsRepository.get(id)

        return CustomerBillDetails(
            id = customerDetails.id,
            customerName = customerDetails.customerName,
            orderItems = ordersList
        )
    }
}