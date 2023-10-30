package com.example.splitthebill.data.usecases.orders

import com.example.splitthebill.domain.entities.customers.CustomerBillDetails
import com.example.splitthebill.domain.entities.orderitem.OrderItem
import com.example.splitthebill.domain.repositories.customers.CreateCustomerBillRepository
import com.example.splitthebill.domain.repositories.customers.UpdateCustomerBillRepository
import com.example.splitthebill.domain.repositories.orders.CreateOrderItemRepository
import com.example.splitthebill.domain.repositories.orders.UpdateOrderItemRepository
import com.example.splitthebill.domain.usecases.customers.CreateOrUpdateCustomerBillUseCase
import com.example.splitthebill.domain.usecases.orders.CreateOrUpdateOrderItemUseCase

class CreateOrUpdateOrderItemImplementantion(
    private val createOrderItemRepository: CreateOrderItemRepository,
    private val updateOrderItemRepository: UpdateOrderItemRepository
) : CreateOrUpdateOrderItemUseCase {
    override suspend fun execute(orderItem: OrderItem, customerId: Number) {
        if (orderItem.id == null) {
            createOrderItemRepository.create(orderItem, customerId)
        } else {
            updateOrderItemRepository.update(orderItem, customerId)
        }
    }
}