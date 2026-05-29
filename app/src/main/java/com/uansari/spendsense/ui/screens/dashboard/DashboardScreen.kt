package com.uansari.spendsense.ui.screens.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.uansari.spendsense.data.SampleData
import com.uansari.spendsense.ui.components.SummaryCard
import com.uansari.spendsense.ui.components.TransactionItem

@Composable
fun DashboardScreen(
    onTransactionClick: (Int) -> Unit, modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 32.dp, start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        item {
            Text(
                text = "Overview",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
        }

        // Balance card (full width)
        item {
            SummaryCard(
                label = "Current Balance",
                value = "£%.2f".format(SampleData.balance),
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Income + Expense side by side
        item {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                SummaryCard(
                    label = "Income",
                    value = "£%.2f".format(SampleData.totalIncome),
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    modifier = Modifier.weight(1f)
                )
                SummaryCard(
                    label = "Expenses",
                    value = "£%.2f".format(SampleData.totalExpenses),
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        item {
            Text(
                text = "Recent Transactions",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
        }

        // Show last 4 transactions
        items(SampleData.transactions.takeLast(4)) { transaction ->
            TransactionItem(
                transaction = transaction, onClick = { onTransactionClick(transaction.id) })
        }
    }
}