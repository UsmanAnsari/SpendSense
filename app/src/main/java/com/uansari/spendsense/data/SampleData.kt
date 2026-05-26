package com.uansari.spendsense.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBalance
import androidx.compose.material.icons.outlined.DirectionsBus
import androidx.compose.material.icons.outlined.FitnessCenter
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MusicNote
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.outlined.Tv
import androidx.compose.material.icons.outlined.Work
import com.uansari.spendsense.model.Transaction
import com.uansari.spendsense.model.TransactionType

object SampleData {

    val transactions = listOf(
        Transaction(
            id = 1,
            title = "Salary",
            amount = 3200.00,
            type = TransactionType.INCOME,
            category = "Income",
            date = "1 May 2026",
            icon = Icons.Outlined.AccountBalance,
            note = "Monthly salary from Acme Ltd"
        ), Transaction(
            id = 2,
            title = "Rent",
            amount = 950.00,
            type = TransactionType.EXPENSE,
            category = "Housing",
            date = "2 May 2026",
            icon = Icons.Outlined.Home,
            note = "Monthly flat rent"
        ), Transaction(
            id = 3,
            title = "Tesco",
            amount = 67.40,
            type = TransactionType.EXPENSE,
            category = "Groceries",
            date = "5 May 2026",
            icon = Icons.Outlined.ShoppingCart,
            note = "Weekly grocery shop"
        ), Transaction(
            id = 4,
            title = "Spotify",
            amount = 9.99,
            type = TransactionType.EXPENSE,
            category = "Entertainment",
            date = "8 May 2026",
            icon = Icons.Outlined.MusicNote,
            note = "Monthly premium subscription"
        ), Transaction(
            id = 5,
            title = "Freelance",
            amount = 450.00,
            type = TransactionType.INCOME,
            category = "Income",
            date = "12 May 2026",
            icon = Icons.Outlined.Work,
            note = "UI design contract"
        ), Transaction(
            id = 6,
            title = "Netflix",
            amount = 15.99,
            type = TransactionType.EXPENSE,
            category = "Entertainment",
            date = "15 May 2026",
            icon = Icons.Outlined.Tv,
            note = "Standard HD plan"
        ), Transaction(
            id = 7,
            title = "Transport",
            amount = 89.00,
            type = TransactionType.EXPENSE,
            category = "Transport",
            date = "16 May 2026",
            icon = Icons.Outlined.DirectionsBus,
            note = "Monthly bus pass"
        ), Transaction(
            id = 8,
            title = "Gym",
            amount = 35.00,
            type = TransactionType.EXPENSE,
            category = "Health",
            date = "18 May 2026",
            icon = Icons.Outlined.FitnessCenter,
            note = "Monthly membership"
        )
    )

    val totalIncome
        get() = transactions.filter { it.type == TransactionType.INCOME }.sumOf { it.amount }

    val totalExpenses
        get() = transactions.filter { it.type == TransactionType.EXPENSE }.sumOf { it.amount }

    val balance get() = totalIncome - totalExpenses
}
