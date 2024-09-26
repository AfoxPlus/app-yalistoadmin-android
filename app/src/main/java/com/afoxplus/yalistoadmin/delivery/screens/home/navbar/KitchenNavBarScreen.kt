package com.afoxplus.yalistoadmin.delivery.screens.home.navbar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.afoxplus.uikit.designsystem.atoms.UIKitText
import com.afoxplus.uikit.designsystem.foundations.UIKitTheme
import com.afoxplus.uikit.designsystem.molecules.UIKitTabItem
import com.afoxplus.yalistoadmin.delivery.screens.ShareTabOrdersScreen
import com.afoxplus.yalistoadmin.delivery.viewmodels.HomeViewModel
import com.afoxplus.yalistoadmin.domain.entities.Order
import kotlinx.coroutines.launch

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

    KitchenStatesTabs(
        modifier = Modifier.padding(horizontal = UIKitTheme.spacing.spacing16),
        tabItems = tabItems.value
    ) { _, tabItem -> tabItem.screen() }
}

@Composable
fun KitchenStatesTabs(
    modifier: Modifier = Modifier,
    tabItems: List<UIKitTabItem>,
    onTabSelected: @Composable (index: Int, tabItem: UIKitTabItem) -> Unit
) {
    val pagerState = rememberPagerState { tabItems.size }
    val selectedTabIndex = remember { derivedStateOf { pagerState.currentPage } }
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(
            modifier = modifier,
            selectedTabIndex = selectedTabIndex.value,
            divider = {},
            containerColor = Color.Transparent
        ) {
            tabItems.forEachIndexed { index, item ->
                Tab(
                    selected = selectedTabIndex.value == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(tabItems.indexOf(item))
                        }
                    },
                    text = {
                        UIKitText(
                            text = item.title,
                            style = UIKitTheme.typography.header05Bold
                        )
                    }
                )
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth()
        ) {
            onTabSelected(selectedTabIndex.value, tabItems[selectedTabIndex.value])
        }
    }
}