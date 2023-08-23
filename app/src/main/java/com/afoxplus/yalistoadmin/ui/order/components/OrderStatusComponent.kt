package com.afoxplus.yalistoadmin.ui.order.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.afoxplus.uikitcompose.ui.theme.Dark02
import com.afoxplus.uikitcompose.ui.theme.Dark04
import com.afoxplus.uikitcompose.ui.theme.Dark05
import com.afoxplus.uikitcompose.ui.theme.Header02Bold
import com.afoxplus.uikitcompose.ui.theme.Header05
import com.afoxplus.uikitcompose.ui.theme.Light03
import com.afoxplus.uikitcompose.ui.theme.Paragraph01
import com.afoxplus.uikitcompose.ui.theme.Paragraph02
import com.afoxplus.uikitcompose.ui.theme.UiKitComposeTheme
import com.afoxplus.yalistoadmin.R

@Composable
fun OrderDetailItem(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    price: String,
    quantity: String,
    total: String
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp, Alignment.Top),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = title,
            color = Dark02,
            style = Header05
        )
        Text(
            text = description,
            color = Dark04,
            style = Paragraph01
        )
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = price,
                color = Dark02,
                style = Paragraph01
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = quantity,
                color = Dark02,
                style = Paragraph01
            )
            Text(
                modifier = Modifier.weight(1f),
                text = total,
                color = Dark02,
                textAlign = TextAlign.End,
                style = Paragraph01
            )
        }
    }
}

@Composable
fun OrderDetailTotalItem(modifier: Modifier = Modifier, total: String, paymentMethod: String) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.End
    ) {
        Text(
            text = stringResource(id = R.string.order_status_total_label),
            color = Dark02,
            style = Paragraph01
        )
        Text(
            text = total,
            color = Dark02,
            style = Header02Bold
        )
        Text(
            text = stringResource(id = R.string.order_status_payment_method_label),
            color = Dark02,
            style = Paragraph01
        )
        Text(
            text = paymentMethod,
            color = Dark05,
            style = Paragraph02
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OrderDetailItemPreview() {
    UiKitComposeTheme {
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
            OrderDetailTotalItem(total = "S/ 50.30", paymentMethod = "Efectivo")
        }
    }
}
