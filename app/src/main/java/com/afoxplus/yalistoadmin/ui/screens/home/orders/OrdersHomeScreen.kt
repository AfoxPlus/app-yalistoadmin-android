package com.afoxplus.yalistoadmin.ui.screens.home.orders

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.afoxplus.uikit.designsystem.components.UIKitTabItem
import com.afoxplus.uikit.designsystem.components.UIKitTabs
import com.afoxplus.uikit.designsystem.theme.UIKitTheme
import com.afoxplus.yalistoadmin.domain.entities.Order
import com.afoxplus.yalistoadmin.ui.screens.orders.OrderScreen
import com.afoxplus.yalistoadmin.ui.screens.orders.OrdersStatusViewModel

@Composable
fun OrdersHomeScreen(
    viewModel: OrdersStatusViewModel,
    navigateTo: (Order) -> Unit
) {
    LaunchedEffect(key1 = Unit) { viewModel.getStatesTabOrder() }
    val states by viewModel.statesTabOrder.collectAsStateWithLifecycle()
    val tabItems = remember {
        derivedStateOf {
            states.map { state ->
                UIKitTabItem(
                    title = state.name,
                    screen = {
                        OrderScreen(
                            viewModel = viewModel,
                            stateId = state.id,
                            navigateTo = navigateTo
                        )
                    }
                )
            }
        }
    }

    UIKitTabs(
        modifier = Modifier.padding(horizontal = UIKitTheme.spacing.spacing16),
        tabItems = tabItems.value
    ) { index ->
        tabItems.value[index].screen()
    }
}
