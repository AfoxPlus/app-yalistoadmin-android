package com.afoxplus.yalistoadmin.delivery.screens.home.navbar

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.afoxplus.uikit.designsystem.foundations.UIKitTheme
import com.afoxplus.uikit.designsystem.molecules.UIKitTabItem
import com.afoxplus.uikit.designsystem.molecules.UIKitTabs
import com.afoxplus.yalistoadmin.delivery.screens.ShareTabOrdersScreen
import com.afoxplus.yalistoadmin.delivery.viewmodels.HomeViewModel
import com.afoxplus.yalistoadmin.domain.entities.Order

@Composable
fun KitchenHomeScreen(
    viewModel: HomeViewModel,
    navigateToOrderDetail: (Order) -> Unit,
    navigateToOrderDetailAdmin: (Order) -> Unit
) {
    LaunchedEffect(key1 = Unit) { viewModel.getKitchenOrderStates() }
    val states by viewModel.statesTabKitchen.collectAsStateWithLifecycle()
    val tabItems = remember {
        derivedStateOf {
            states.map { state ->
                UIKitTabItem(
                    title = state.name,
                    screen = {
                        ShareTabOrdersScreen(
                            stateId = state.id,
                            navigateToOrderDetail = navigateToOrderDetail,
                            navigateToOrderDetailAdmin = navigateToOrderDetailAdmin
                        )
                    }
                )
            }
        }
    }

    UIKitTabs(
        modifier = Modifier.padding(horizontal = UIKitTheme.spacing.spacing16),
        tabItems = tabItems.value
    ) { _, tabItem -> tabItem.screen() }
}
