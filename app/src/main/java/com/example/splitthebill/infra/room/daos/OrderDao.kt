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
}