package com.example.splitthebill.di

import android.content.Context
import com.example.splitthebill.data.repositories.customers.CreateCustomerBillRepositoryImplementation
import com.example.splitthebill.data.repositories.customers.GetCustomerDetailsRepositoryImplementation
import com.example.splitthebill.data.repositories.customers.GetCustomersBillsRepositoryImplementation
import com.example.splitthebill.data.repositories.customers.UpdateCustomerBillRepositoryImplementation
import com.example.splitthebill.data.repositories.orders.GetCustomerOrdersByIdRepositoryImplementation
import com.example.splitthebill.data.usecases.customers.CreateOrUpdateCustomerBillImplementantion
import com.example.splitthebill.data.usecases.customers.GetCustomerBillDetailsImplementation
import com.example.splitthebill.data.usecases.customers.GetCustomersBillsImplementation
import com.example.splitthebill.domain.repositories.customers.CustomerRepository
import com.example.splitthebill.domain.usecases.customers.CustomerUseCases

interface AppModule {
    val customerUseCases: CustomerUseCases
}
class AppModuleImplementation(
    private val appContext: Context
): AppModule {
    private val customerRepository: CustomerRepository by lazy {
        CustomerRepository(
            getCustomersBillsRepository = GetCustomersBillsRepositoryImplementation(),
            getCustomerDetailsRepository = GetCustomerDetailsRepositoryImplementation(),
            createCustomerBillRepository = CreateCustomerBillRepositoryImplementation(),
            updateCustomerBillRepository = UpdateCustomerBillRepositoryImplementation()
        )
    }

    override val customerUseCases: CustomerUseCases by lazy {
        CustomerUseCases(
            getCustomerBillDetails = GetCustomerBillDetailsImplementation(
                getCustomerOrdersByIdRepository = GetCustomerOrdersByIdRepositoryImplementation(),
                getCustomerDetailsRepository = customerRepository.getCustomerDetailsRepository
            ),
            getCustomersBills = GetCustomersBillsImplementation(
                getCustomersBillsRepository =customerRepository.getCustomersBillsRepository
            ),
            createOrUpdateCustomerBill = CreateOrUpdateCustomerBillImplementantion(
                createCustomerBillRepository = customerRepository.createCustomerBillRepository,
                updateCustomerBillRepository = customerRepository.updateCustomerBillRepository
            )
        )
    }
}