package com.example.splitthebill.di

import android.content.Context
import com.example.splitthebill.data.repositories.customers.CustomersRepository
import com.example.splitthebill.data.repositories.orders.GetCustomerOrdersByIdRepositoryImplementation
import com.example.splitthebill.data.usecases.customers.CreateOrUpdateCustomerBillImplementantion
import com.example.splitthebill.data.usecases.customers.GetCustomerBillDetailsImplementation
import com.example.splitthebill.data.usecases.customers.GetCustomersBillsImplementation
import com.example.splitthebill.domain.usecases.customers.CustomerUseCases
import com.example.splitthebill.infra.room.AppDatabase

interface AppModule {
    val customerUseCases: CustomerUseCases
}

class AppModuleImplementation(
    private val appContext: Context
): AppModule {
    private val appDatabase: AppDatabase by lazy {
        AppDatabase.getDatabase(appContext)
    }

    private val customerRepository: CustomersRepository by lazy {
        CustomersRepository(appDatabase.customerDao())
    }

    override val customerUseCases: CustomerUseCases by lazy {
        CustomerUseCases(
            getCustomerBillDetails = GetCustomerBillDetailsImplementation(
                getCustomerOrdersByIdRepository = GetCustomerOrdersByIdRepositoryImplementation(appDatabase.orderDao()),
                getCustomerDetailsRepository = customerRepository
            ),
            getCustomersBills = GetCustomersBillsImplementation(
                getCustomersBillsRepository = customerRepository,
                getCustomerOrdersById = GetCustomerOrdersByIdRepositoryImplementation(appDatabase.orderDao())
            ),
            createOrUpdateCustomerBill = CreateOrUpdateCustomerBillImplementantion(
                createCustomerBillRepository = customerRepository,
                updateCustomerBillRepository = customerRepository
            )
        )
    }
}