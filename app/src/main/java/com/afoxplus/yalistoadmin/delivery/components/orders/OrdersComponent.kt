package com.afoxplus.yalistoadmin.delivery.components.orders

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.afoxplus.uikit.designsystem.foundations.UIKitTheme
import com.afoxplus.yalistoadmin.domain.entities.Order

@Composable
fun OrdersComponent(
    orders: List<Order>,
    modifier: Modifier = Modifier,
    onClick: (Order) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(color = UIKitTheme.colors.light01),
        verticalArrangement = Arrangement.spacedBy(UIKitTheme.spacing.spacing10),
        contentPadding = PaddingValues(
            horizontal = UIKitTheme.spacing.spacing08,
            vertical = UIKitTheme.spacing.spacing16
        )
    ) {
        items(orders.size) {
            ItemOrderComponent(
                modifier = Modifier.fillMaxWidth(),
                order = orders[it],
                onClick = { order ->
                    onClick(order)
                }
            )
        }
    }
}
