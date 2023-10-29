package com.example.splitthebill.infra.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "order",
    foreignKeys = [
        ForeignKey(
            entity = Customer::class,
            parentColumns = ["id"],
            childColumns = ["customer_id"],
            onDelete = ForeignKey.CASCADE
        )
    ])
data class Order(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val quantity: Int,
    val name: String,
    val price: Double,
    @ColumnInfo(name = "customer_id")
    val customerId: Long
)