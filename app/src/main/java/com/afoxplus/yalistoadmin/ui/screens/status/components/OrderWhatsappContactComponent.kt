package com.afoxplus.yalistoadmin.ui.screens.status.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.afoxplus.uikit.designsystem.extensions.noRippleClickable
import com.afoxplus.uikit.designsystem.foundations.UIKitTheme
import com.afoxplus.yalistoadmin.R

@Composable
fun OrderWhatsappContactComponent(
    modifier: Modifier = Modifier,
    phoneNumber: String,
    clientName: String,
    description: String = "",
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .noRippleClickable { onClick() },
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(containerColor = UIKitTheme.colors.light01)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = clientName,
                    color = UIKitTheme.colors.blueGray800,
                    style = UIKitTheme.typography.paragraph01SemiBold
                )
                Text(
                    text = phoneNumber,
                    color = UIKitTheme.colors.blueGray800,
                    style = UIKitTheme.typography.paragraph01SemiBold
                )
                if (description.isNotEmpty()) {
                    Text(
                        text = description,
                        color = UIKitTheme.colors.gray600,
                        style = UIKitTheme.typography.paragraph01
                    )
                }
            }
            Icon(
                modifier = Modifier
                    .padding(1.dp)
                    .width(36.dp)
                    .height(36.dp),
                painter = painterResource(id = R.drawable.logo_whatsapp_icon),
                contentDescription = "logo_whatsapp_icon",
                tint = UIKitTheme.colors.green400
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrderWhatsappContactComponentPreview() {
    UIKitTheme {
        Column {
            OrderWhatsappContactComponent(
                phoneNumber = "932534599",
                clientName = "Petter Garcia",
                description = "Av. Los Faisanes 354"
            )
            OrderWhatsappContactComponent(
                clientName = "Petter Garcia",
                phoneNumber = "932534599"
            )
        }
    }
}
