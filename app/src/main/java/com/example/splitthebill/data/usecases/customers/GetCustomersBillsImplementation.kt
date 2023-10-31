package com.example.splitthebill.data.usecases.customers

import com.example.splitthebill.domain.entities.customers.CustomerBill
import com.example.splitthebill.domain.entities.customers.CustomerBillDetails
import com.example.splitthebill.domain.entities.customers.CustomerDetailsFromRepository
import com.example.splitthebill.domain.repositories.customers.GetCustomersBillsRepository
import com.example.splitthebill.domain.repositories.orders.GetCustomerOrdersByIdRepository
import com.example.splitthebill.domain.usecases.customers.GetCustomersBillsUseCase

class GetCustomersBillsImplementation(
    private val getCustomersBillsRepository: GetCustomersBillsRepository,
    private val getCustomerOrdersById: GetCustomerOrdersByIdRepository
) :
    GetCustomersBillsUseCase {
    override suspend fun getCustomersBills(): List<CustomerBill> {
        val customers = getCustomersBillsRepository.get()
        val parsedCustomers = customers.map {
            val orders = getCustomerOrdersById.getOrders(it.id!!.toInt())
            val totalPrice = orders.sumOf { it.price.toInt() * it.quantity.toInt() }
            val ordersQuantity = orders.sumOf { it.quantity.toInt() }
            CustomerBill(
                id = it.id,
                customerName = it.customerName,
                totalPrice = totalPrice,
                orderQuantity = ordersQuantity,
                orders = orders
            )
        }
        return parsedCustomers
    }
}