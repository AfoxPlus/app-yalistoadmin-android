package com.afoxplus.uikitdemo.demos

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.afoxplus.uikitcompose.ui.components.CardOrderTypeComponent
import com.afoxplus.uikitcompose.ui.components.OrderType
import com.afoxplus.uikitcompose.ui.components.OrderTypeVO

@Composable
fun CardOrderTypeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        CardOrderTypeComponent(
            modifier=Modifier.fillMaxWidth(),
            orderTypeVO = OrderTypeVO("Mesa", "01"),
            orderType = OrderType.Table()
        )
        Spacer(modifier = Modifier.height(10.dp))
        CardOrderTypeComponent(
            modifier=Modifier.fillMaxWidth(),
            orderTypeVO = OrderTypeVO("Delivery"),
            orderType = OrderType.Delivery()
        )
        Spacer(modifier = Modifier.height(10.dp))
        CardOrderTypeComponent(
            modifier=Modifier.fillMaxWidth(),
            orderTypeVO = OrderTypeVO("Total", "S/ 999.80"),
            orderType = OrderType.Amount()
        )
    }
}

@Preview
@Composable
fun CardOrderTypeScreenPreview() {
    CardOrderTypeScreen()
}
