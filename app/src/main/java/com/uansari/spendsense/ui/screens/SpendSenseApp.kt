package com.uansari.spendsense.ui.screens

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.uansari.spendsense.navigation.AppNavHost
import com.uansari.spendsense.navigation.topLevelRoutes

@Composable
fun SpendSenseApp(
    windowSizeClass: WindowSizeClass,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination

    // Track selected transaction for list-detail on Expanded
    var selectedTransactionId by remember { mutableStateOf<Int?>(null) }

    val isExpanded = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Expanded

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            topLevelRoutes.forEach { route ->
                val isSelected = currentDestination?.route
                    ?.contains(route.route::class.qualifiedName ?: "")
                    ?: false

                item(
                    selected = isSelected,
                    onClick = {
                        navController.navigate(route.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = if (isSelected) route.selectedIcon
                                          else route.icon,
                            contentDescription = route.label
                        )
                    },
                    label = { Text(route.label) }
                )
            }
        },
        modifier = modifier
    ) {
        // Content area
        AppNavHost(
            navController = navController,
            isExpanded = isExpanded,
            selectedTransactionId = selectedTransactionId,
            onTransactionSelected = { id -> selectedTransactionId = id }
        )
    }
}