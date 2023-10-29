package com.example.splitthebill.data.repositories.orders

import com.example.splitthebill.domain.entities.customers.CustomerBill
import com.example.splitthebill.domain.entities.orderitem.OrderItem
import com.example.splitthebill.domain.repositories.orders.GetCustomerOrdersByIdRepository
import com.example.splitthebill.infra.room.daos.OrderDao
import com.example.splitthebill.infra.room.entities.Order

class GetCustomerOrdersByIdRepositoryImplementation(private val orderDao: OrderDao) :
    GetCustomerOrdersByIdRepository {
    override suspend fun getOrders(id: Number): List<OrderItem> {
        return orderDao.getOrdersForCustomer(id.toLong()).map {
            OrderItem(id = it.id, name = it.name, price = it.price, quantity = it.quantity)
        }
    }
}