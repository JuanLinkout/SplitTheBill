package com.example.splitthebill.di

import android.content.Context
import com.example.splitthebill.data.repositories.customers.CustomersRepository
import com.example.splitthebill.data.repositories.orders.GetCustomerOrdersByIdRepositoryImplementation
import com.example.splitthebill.data.repositories.orders.OrdersRepository
import com.example.splitthebill.data.usecases.customers.CreateOrUpdateCustomerBillImplementantion
import com.example.splitthebill.data.usecases.customers.DeleteAllCustomersBillsImplementation
import com.example.splitthebill.data.usecases.customers.DeleteCustomerBillImplementation
import com.example.splitthebill.data.usecases.customers.GetCustomerBillDetailsImplementation
import com.example.splitthebill.data.usecases.customers.GetCustomersBillsImplementation
import com.example.splitthebill.data.usecases.customers.SplitCustomerBillImplementation
import com.example.splitthebill.data.usecases.orders.CreateOrUpdateOrderItemImplementantion
import com.example.splitthebill.data.usecases.orders.DeleteOrderItemImplementantion
import com.example.splitthebill.domain.usecases.customers.CustomerUseCases
import com.example.splitthebill.domain.usecases.orders.CreateOrUpdateOrderItemUseCase
import com.example.splitthebill.domain.usecases.orders.OrdersUseCases
import com.example.splitthebill.infra.room.AppDatabase

interface AppModule {
    val customerUseCases: CustomerUseCases
    val ordersUseCases: OrdersUseCases
}

class AppModuleImplementation(
    private val appContext: Context
) : AppModule {
    private val appDatabase: AppDatabase by lazy {
        AppDatabase.getDatabase(appContext)
    }

    private val customerRepository: CustomersRepository by lazy {
        CustomersRepository(appDatabase.customerDao())
    }

    private val ordersRepository: OrdersRepository by lazy {
        OrdersRepository(appDatabase.orderDao())
    }

    override val ordersUseCases: OrdersUseCases by lazy {
        OrdersUseCases(
            createOrUpdateOrderItemUseCase = CreateOrUpdateOrderItemImplementantion(
                createOrderItemRepository = ordersRepository,
                updateOrderItemRepository = ordersRepository
            ),
            deleteOrderItemUseCase = DeleteOrderItemImplementantion(
                deleteOrderItemRepository = ordersRepository
            )
        )
    }

    override val customerUseCases: CustomerUseCases by lazy {
        CustomerUseCases(
            getCustomerBillDetails = GetCustomerBillDetailsImplementation(
                getCustomerOrdersByIdRepository = GetCustomerOrdersByIdRepositoryImplementation(
                    appDatabase.orderDao()
                ),
                getCustomerDetailsRepository = customerRepository
            ),
            getCustomersBills = GetCustomersBillsImplementation(
                getCustomersBillsRepository = customerRepository,
                getCustomerOrdersById = GetCustomerOrdersByIdRepositoryImplementation(appDatabase.orderDao())
            ),
            createOrUpdateCustomerBill = CreateOrUpdateCustomerBillImplementantion(
                createCustomerBillRepository = customerRepository,
                updateCustomerBillRepository = customerRepository
            ),
            deleteCustomerBillUseCase = DeleteCustomerBillImplementation(
                deleteAllOrderItemsRepository = ordersRepository,
                deleteCustomerBillRepository = customerRepository
            ),
            splitCustomerBillsUseCase = SplitCustomerBillImplementation(),
            deleteAllCustomersBillsUseCase = DeleteAllCustomersBillsImplementation(
                deleteAllOrderItemsRepository = ordersRepository,
                deleteAllCustomersBillsRepository = customerRepository
            )
        )
    }
}