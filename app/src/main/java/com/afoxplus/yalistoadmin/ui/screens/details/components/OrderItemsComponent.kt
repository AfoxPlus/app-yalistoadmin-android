package com.afoxplus.yalistoadmin.ui.screens.details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.afoxplus.uikitcompose.ui.theme.Light03
import com.afoxplus.uikitcompose.ui.theme.UiKitComposeTheme

@Composable
fun OrderItemsComponent(
    modifier: Modifier = Modifier
) {
    Column {
        OrderDetailItem(
            title = "Jarra chicha morada",
            description = "Refrescante chicha morada 1 litro",
            price = "S/ 20.50",
            quantity = "Cant: 1",
            total = "S/ 20.50"
        )
        Divider(modifier = Modifier.height(1.dp), color = Light03)
        OrderDetailItem(
            title = "Jarra de limonada fresca",
            description = "Refrescante limonada 1 litro",
            price = "S/ 20.50",
            quantity = "Cant: 2",
            total = "S/ 51.00"
        )
        Divider(modifier = Modifier.height(1.dp), color = Light03)
        OrderDetailItem(
            title = "Jarra de limonada fresca",
            description = "Refrescante limonada 1 litro",
            price = "S/ 20.50",
            quantity = "Cant: 2",
            total = "S/ 51.00"
        )
        Divider(modifier = Modifier.height(1.dp), color = Light03)
        OrderDetailItem(
            title = "Jarra de limonada fresca",
            description = "Refrescante limonada 1 litro",
            price = "S/ 20.50",
            quantity = "Cant: 2",
            total = "S/ 51.00"
        )
        Divider(modifier = Modifier.height(1.dp), color = Light03)
        OrderDetailItem(
            title = "Jarra de limonada fresca",
            description = "Refrescante limonada 1 litro",
            price = "S/ 20.50",
            quantity = "Cant: 2",
            total = "S/ 51.00"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OrderItemsComponentPreview() {
    UiKitComposeTheme {
        OrderItemsComponent()
    }
}
