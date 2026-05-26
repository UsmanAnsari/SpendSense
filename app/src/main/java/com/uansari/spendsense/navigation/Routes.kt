package com.uansari.spendsense.navigation

import kotlinx.serialization.Serializable

@Serializable object Dashboard
@Serializable object Transactions
@Serializable object Settings
@Serializable data class TransactionDetail(val transactionId: Int)
