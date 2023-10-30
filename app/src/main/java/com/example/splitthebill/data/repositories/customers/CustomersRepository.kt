package com.example.splitthebill.data.repositories.customers

import androidx.room.Dao
import com.example.splitthebill.domain.entities.customers.CustomerBillDetails
import com.example.splitthebill.domain.entities.customers.CustomerDetailsFromRepository
import com.example.splitthebill.domain.repositories.customers.CreateCustomerBillRepository
import com.example.splitthebill.domain.repositories.customers.DeleteCustomerBillRepository
import com.example.splitthebill.domain.repositories.customers.GetCustomersBillsRepository
import com.example.splitthebill.domain.repositories.customers.GetCustomersDetailsRepository
import com.example.splitthebill.domain.repositories.customers.UpdateCustomerBillRepository
import com.example.splitthebill.infra.room.daos.CustomerDao
import com.example.splitthebill.infra.room.entities.Customer

@Dao
class CustomersRepository(private val customerDao: CustomerDao) :
    GetCustomersBillsRepository, GetCustomersDetailsRepository,
    CreateCustomerBillRepository, UpdateCustomerBillRepository, DeleteCustomerBillRepository {
    override suspend fun create(customerBill: CustomerBillDetails) {
        customerDao.insertCustomer(Customer(name = customerBill.customerName))
    }

    override suspend fun get(): List<CustomerDetailsFromRepository> {
        return customerDao.getCustomersBills().map {
            CustomerDetailsFromRepository(id = it.id, customerName = it.name)
        }
    }

    override suspend fun get(id: Number): CustomerDetailsFromRepository {
        val customer = customerDao.getCustomerById(id.toLong())[0]
        return CustomerDetailsFromRepository(id = customer.id, customerName = customer.name)
    }

    override suspend fun update(customerBill: CustomerBillDetails) {
        customerDao.updateCustomer(
            Customer(
                id = customerBill.id!!.toLong(),
                name = customerBill.customerName
            )
        )
    }

    override suspend fun delete(customerBillId: Number) {
        customerDao.deleteCustomerBill(customerBillId.toLong())
    }
}