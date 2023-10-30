package com.example.splitthebill.data.usecases.orders

import com.example.splitthebill.domain.entities.customers.CustomerBillDetails
import com.example.splitthebill.domain.entities.orderitem.OrderItem
import com.example.splitthebill.domain.repositories.customers.CreateCustomerBillRepository
import com.example.splitthebill.domain.repositories.customers.UpdateCustomerBillRepository
import com.example.splitthebill.domain.repositories.orders.CreateOrderItemRepository
import com.example.splitthebill.domain.repositories.orders.DeleteOrderItemRepository
import com.example.splitthebill.domain.repositories.orders.UpdateOrderItemRepository
import com.example.splitthebill.domain.usecases.customers.CreateOrUpdateCustomerBillUseCase
import com.example.splitthebill.domain.usecases.orders.CreateOrUpdateOrderItemUseCase
import com.example.splitthebill.domain.usecases.orders.DeleteOrderItemUseCase

class DeleteOrderItemImplementantion(
    private val deleteOrderItemRepository: DeleteOrderItemRepository,
) : DeleteOrderItemUseCase {
    override suspend fun delete(orderItemId: Number) {
        deleteOrderItemRepository.delete(orderItemId)
    }
}