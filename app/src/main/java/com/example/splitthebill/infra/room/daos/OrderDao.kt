package com.example.splitthebill.infra.room.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.splitthebill.infra.room.entities.Order

@Dao
interface OrderDao {
    @Insert
    suspend fun insertOrder(order: Order)

    @Update
    suspend fun updateOrder(order: Order)

    @Query("SELECT * FROM `order` WHERE customer_id = :customerId")
    suspend fun getOrdersForCustomer(customerId: Long): List<Order>

    @Query("DELETE FROM `order` WHERE id = :orderId")
    suspend fun deleteOrder(orderId: Long): Unit

    @Query("DELETE FROM `order` WHERE customer_id = :customerId")
    suspend fun deleteAllOrders(customerId: Long): Unit
}