package com.afoxplus.yalistoadmin.ui.screens.details.components

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
import com.afoxplus.uikitcompose.ui.theme.Dark01
import com.afoxplus.uikitcompose.ui.theme.Dark02
import com.afoxplus.uikitcompose.ui.theme.Green02
import com.afoxplus.uikitcompose.ui.theme.Light01
import com.afoxplus.uikitcompose.ui.theme.Paragraph01
import com.afoxplus.uikitcompose.ui.theme.Paragraph01SemiBold
import com.afoxplus.uikitcompose.ui.theme.UiKitComposeTheme
import com.afoxplus.yalistoadmin.R
import com.afoxplus.yalistoadmin.commons.extensions.noRippleClickable

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
        colors = CardDefaults.cardColors(containerColor = Light01)
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
                    color = Dark02,
                    style = Paragraph01SemiBold
                )
                Text(
                    text = phoneNumber,
                    color = Dark02,
                    style = Paragraph01SemiBold
                )
                if (description.isNotEmpty()) {
                    Text(
                        text = description,
                        color = Dark01,
                        style = Paragraph01
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
                tint = Green02
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrderWhatsappContactComponentPreview() {
    UiKitComposeTheme {
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
