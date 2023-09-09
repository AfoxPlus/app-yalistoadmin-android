package com.afoxplus.yalistoadmin.ui.orders.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.afoxplus.uikitcompose.ui.theme.Light01
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
            .background(color = Light01),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 16.dp)
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

@Preview(showSystemUi = true)
@Composable
fun OrdersComponentPreview() {
    OrdersComponent(orders = arrayListOf()) {}
}
