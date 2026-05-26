package com.uansari.spendsense.model

import androidx.compose.ui.graphics.vector.ImageVector

data class Transaction(
    val id: Int,
    val title: String,
    val amount: Double,
    val type: TransactionType,
    val category: String,
    val date: String,
    val icon: ImageVector,
    val note: String
)
