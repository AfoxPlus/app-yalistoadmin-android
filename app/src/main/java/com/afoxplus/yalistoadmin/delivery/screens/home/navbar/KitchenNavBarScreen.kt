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
import com.afoxplus.yalistoadmin.delivery.routers.BottomBarHomeRouter
import com.afoxplus.yalistoadmin.delivery.screens.OrderScreen
import com.afoxplus.yalistoadmin.delivery.viewmodels.HomeViewModel
import com.afoxplus.yalistoadmin.domain.entities.Order

@Composable
fun KitchenHomeScreen(
    route: BottomBarHomeRouter = BottomBarHomeRouter.Kitchen,
    viewModel: HomeViewModel,
    navigateTo: (Order) -> Unit
) {
    LaunchedEffect(key1 = Unit) { viewModel.getStatesTabSale() }
    val states by viewModel.statesTabKitchen.collectAsStateWithLifecycle()
    val tabItems = remember {
        derivedStateOf {
            states.map { state ->
                UIKitTabItem(
                    title = state.name,
                    screen = {
                        OrderScreen(
                            viewModel = viewModel,
                            route = route,
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
