package com.example.splitthebill.infra.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.splitthebill.infra.room.daos.CustomerDao
import com.example.splitthebill.infra.room.daos.OrderDao
import com.example.splitthebill.infra.room.entities.Customer
import com.example.splitthebill.infra.room.entities.Order

@Database(entities = [Customer::class, Order::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun customerDao(): CustomerDao
    abstract fun orderDao(): OrderDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                val database = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                instance = database
                database
            }
        }
    }
}