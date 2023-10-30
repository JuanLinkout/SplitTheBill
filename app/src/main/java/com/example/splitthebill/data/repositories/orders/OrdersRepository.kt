package com.example.splitthebill.data.repositories.orders

import androidx.room.Dao
import com.example.splitthebill.domain.entities.orderitem.OrderItem
import com.example.splitthebill.domain.repositories.orders.CreateOrderItemRepository
import com.example.splitthebill.domain.repositories.orders.DeleteAllOrderItemsRepository
import com.example.splitthebill.domain.repositories.orders.DeleteOrderItemRepository
import com.example.splitthebill.domain.repositories.orders.UpdateOrderItemRepository
import com.example.splitthebill.infra.room.daos.OrderDao
import com.example.splitthebill.infra.room.entities.Order

@Dao
class OrdersRepository(private val orderDao: OrderDao) : CreateOrderItemRepository,
    UpdateOrderItemRepository, DeleteOrderItemRepository, DeleteAllOrderItemsRepository {
    override suspend fun create(orderItem: OrderItem, customerId: Number) {
        orderDao.insertOrder(
            Order(
                name = orderItem.name,
                price = orderItem.price.toDouble(),
                quantity = orderItem.quantity.toInt(),
                customerId = customerId.toLong()
            )
        )
    }

    override suspend fun update(orderItem: OrderItem, customerId: Number) {
        orderDao.updateOrder(Order(
            id = orderItem.id!!.toLong(),
            name = orderItem.name,
            price = orderItem.price.toDouble(),
            quantity = orderItem.quantity.toInt(),
            customerId = customerId.toLong()
        ))
    }

    override suspend fun deleteAll(customerId: Number) {
        orderDao.deleteAllOrders(customerId = customerId.toLong())
    }

    override suspend fun delete(orderItemId: Number) {
        orderDao.deleteOrder(orderItemId.toLong())
    }


}