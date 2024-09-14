package com.afoxplus.yalistoadmin.delivery.components.status

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.afoxplus.uikit.designsystem.atoms.UIKitIcon
import com.afoxplus.uikit.designsystem.atoms.UIKitText
import com.afoxplus.uikit.designsystem.extensions.noRippleClickable
import com.afoxplus.uikit.designsystem.foundations.UIKitColorTheme
import com.afoxplus.uikit.designsystem.foundations.UIKitIconTheme
import com.afoxplus.uikit.designsystem.foundations.UIKitTheme
import com.afoxplus.uikit.designsystem.foundations.UIKitTypographyTheme

@Composable
fun OrderStatusPrint(modifier: Modifier = Modifier, orderState: String, onClickPrint: () -> Unit) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(UIKitTheme.spacing.spacing04),
        colors = CardDefaults.cardColors(containerColor = UIKitTheme.colors.light01)
    ) {
        Row(
            modifier
                .fillMaxWidth()
                .padding(
                    horizontal = UIKitTheme.spacing.spacing12,
                    vertical = UIKitTheme.spacing.spacing06
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(UIKitTheme.spacing.spacing08)
            ) {
                UIKitText(
                    text = "Estado del pedido: ",
                    style = UIKitTypographyTheme.paragraph01,
                    color = UIKitColorTheme.secondaryColor
                )
                OrderChipState(orderState = orderState)
            }
            Box(modifier = Modifier.weight(1f)) {
                OrderPrintButton(modifier = Modifier.align(Alignment.CenterEnd), onClick = onClickPrint)
            }
        }
    }
}

@Composable
fun OrderPrintButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Box(
        modifier = modifier
            .noRippleClickable { onClick() }
            .background(UIKitColorTheme.blue700, RoundedCornerShape(UIKitTheme.spacing.spacing04))
            .size(UIKitTheme.spacing.spacing40)
    ) {
        UIKitIcon(
            modifier = Modifier.align(Alignment.Center),
            icon = UIKitIconTheme.icon_printer_outline,
            tint = UIKitColorTheme.light01
        )
    }
}

@Composable
fun OrderChipState(modifier: Modifier = Modifier, orderState: String) {
    Box(
        modifier = modifier
            .background(UIKitColorTheme.gray200, RoundedCornerShape(UIKitTheme.spacing.spacing04))
            .padding(horizontal = UIKitTheme.spacing.spacing08, vertical = UIKitTheme.spacing.spacing04)
    ) {
        UIKitText(
            text = orderState,
            style = UIKitTypographyTheme.paragraph01Bold,
            color = UIKitColorTheme.gray700
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOrderStatusPrint() {
    UIKitTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            OrderStatusPrint(orderState = "Pendiente") {
            }
        }
    }
}
