package com.uansari.spendsense.ui.screens.transactions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
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
import com.uansari.spendsense.ui.components.TransactionItem

@Composable
fun TransactionsScreen(
    modifier: Modifier = Modifier,
    onTransactionClick: (Int) -> Unit,
    selectedTransactionId: Int? = null,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(), contentPadding = PaddingValues(top = 48.dp)
    ) {
        item {
            Text(
                text = "All Transactions",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(
                    horizontal = 16.dp, vertical = 8.dp
                )
            )
        }

        items(
            items = SampleData.transactions, key = { it.id }) { transaction ->
            TransactionItem(
                transaction = transaction,
                onClick = { onTransactionClick(transaction.id) },
                modifier = if (transaction.id == selectedTransactionId) Modifier.background(
                    MaterialTheme.colorScheme.surfaceVariant
                )
                else Modifier
            )
        }
    }
}