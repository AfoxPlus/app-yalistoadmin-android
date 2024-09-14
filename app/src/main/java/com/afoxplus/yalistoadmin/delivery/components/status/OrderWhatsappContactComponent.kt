package com.afoxplus.yalistoadmin.delivery.components.status

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
    description: String = "",
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
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
                verticalArrangement = Arrangement.spacedBy(UIKitTheme.spacing.spacing04, Alignment.CenterVertically),
                horizontalAlignment = Alignment.Start
            ) {
                if (phoneNumber.isNotEmpty()) {
                    Text(
                        text = phoneNumber,
                        color = UIKitTheme.colors.blueGray800,
                        style = UIKitTheme.typography.paragraph01SemiBold
                    )
                }
                if (description.isNotEmpty()) {
                    Text(
                        text = description,
                        color = UIKitTheme.colors.gray600,
                        style = UIKitTheme.typography.paragraph01
                    )
                }
            }
            if (phoneNumber.isNotEmpty()) {
                Icon(
                    modifier = Modifier
                        .padding(1.dp)
                        .noRippleClickable { onClick() }
                        .width(36.dp)
                        .height(36.dp),
                    painter = painterResource(id = R.drawable.logo_whatsapp_icon),
                    contentDescription = "logo_whatsapp_icon",
                    tint = UIKitTheme.colors.green400
                )
            }
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
                description = "Av. Los Faisanes 354"
            )
            OrderWhatsappContactComponent(
                phoneNumber = "932534599"
            )
        }
    }
}
