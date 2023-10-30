package com.example.splitthebill.domain.usecases.orders

data class OrdersUseCases(
    val createOrUpdateOrderItemUseCase: CreateOrUpdateOrderItemUseCase,
    val deleteOrderItemUseCase: DeleteOrderItemUseCase
)