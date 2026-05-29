package com.uansari.spendsense.ui.screens.transactions

import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun TransactionsListDetailLayout(
    selectedTransactionId: Int?,
    onTransactionSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val navigator = rememberListDetailPaneScaffoldNavigator<Nothing>()

    ListDetailPaneScaffold(
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        listPane = {
            AnimatedPane {
                TransactionsScreen(
                    onTransactionClick = { id ->
                        onTransactionSelected(id)
//                        navigator.navigateTo(ListDetailPaneScaffoldRole.Detail)
                    },
                    selectedTransactionId = selectedTransactionId
                )
            }
        },
        detailPane = {
            AnimatedPane {
                TransactionDetailScreen(
                    transactionId = selectedTransactionId
                )
            }
        },
        modifier = modifier
    )
}