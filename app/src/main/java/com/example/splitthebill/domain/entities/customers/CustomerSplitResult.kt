package com.example.splitthebill.domain.entities.customers

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize

data class CustomerSplitResult(
    val customerId: Number,
    val customerName: String,
    val valueAmount: Number
) : Parcelable
