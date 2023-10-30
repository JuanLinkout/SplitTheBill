package com.example.splitthebill.infra.room.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.splitthebill.domain.entities.customers.CustomerBill
import com.example.splitthebill.domain.entities.customers.CustomerDetailsFromRepository
import com.example.splitthebill.infra.room.entities.Customer

@Dao
interface CustomerDao {
    @Insert
    suspend fun insertCustomer(customer: Customer)

    @Update
    suspend fun updateCustomer(customer: Customer)

    @Query("SELECT * FROM customer c")
    suspend fun getCustomersBills(): List<Customer>

    @Query("SELECT * FROM customer c WHERE id = :customerId")
    suspend fun getCustomerById(customerId: Long): List<Customer>

    @Query("DELETE FROM customer WHERE id = :customerId")
    suspend fun deleteCustomerBill(customerId: Long): Unit
}