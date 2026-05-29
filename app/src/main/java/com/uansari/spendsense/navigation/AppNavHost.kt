package com.uansari.spendsense.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Dashboard
import androidx.compose.material.icons.outlined.Receipt
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.uansari.spendsense.ui.screens.dashboard.DashboardScreen
import com.uansari.spendsense.ui.screens.settings.SettingsScreen
import com.uansari.spendsense.ui.screens.transactions.TransactionDetailScreen
import com.uansari.spendsense.ui.screens.transactions.TransactionsListDetailLayout
import com.uansari.spendsense.ui.screens.transactions.TransactionsScreen

data class TopLevelRoute(
    val route: Any, val label: String, val icon: ImageVector, val selectedIcon: ImageVector
)

val topLevelRoutes = listOf(
    TopLevelRoute(
        route = Dashboard,
        label = "Dashboard",
        icon = Icons.Outlined.Dashboard,
        selectedIcon = Icons.Filled.Dashboard
    ), TopLevelRoute(
        route = Transactions,
        label = "Transactions",
        icon = Icons.Outlined.Receipt,
        selectedIcon = Icons.Filled.Receipt
    ), TopLevelRoute(
        route = Settings,
        label = "Settings",
        icon = Icons.Outlined.Settings,
        selectedIcon = Icons.Filled.Settings
    )
)

@Composable
fun AppNavHost(
    navController: NavHostController,
    isExpanded: Boolean,
    selectedTransactionId: Int?,
    onTransactionSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Dashboard,
        modifier = modifier
    ) {
        composable<Dashboard> {
            DashboardScreen(
                onTransactionClick = { id ->
                    if (isExpanded) {
                        // On Expanded: switch to Transactions tab and select
                        onTransactionSelected(id)
                        navController.navigate(Transactions) {
                            launchSingleTop = true
                        }
                    } else {
                        // On Compact/Medium: navigate to detail screen
                        navController.navigate(TransactionDetail(id))
                    }
                }
            )
        }

        composable<Transactions> {
            if (isExpanded) {
                // Two-pane layout
                TransactionsListDetailLayout(
                    selectedTransactionId = selectedTransactionId,
                    onTransactionSelected = onTransactionSelected
                )
            } else {
                // Single-pane list
                TransactionsScreen(
                    onTransactionClick = { id ->
                        navController.navigate(TransactionDetail(id))
                    }
                )
            }
        }

        composable<TransactionDetail> { backStackEntry ->
            val args = backStackEntry.toRoute<TransactionDetail>()
            TransactionDetailScreen(transactionId = args.transactionId)
        }

        composable<Settings> {
            SettingsScreen()
        }
    }
}