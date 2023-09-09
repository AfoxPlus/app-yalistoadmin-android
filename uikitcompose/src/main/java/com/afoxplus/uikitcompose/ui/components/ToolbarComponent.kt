package com.afoxplus.uikitcompose.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.afoxplus.uikitcompose.R
import com.afoxplus.uikitcompose.ui.theme.Dark01
import com.afoxplus.uikitcompose.ui.theme.Dark02
import com.afoxplus.uikitcompose.ui.theme.Header04Bold
import com.afoxplus.uikitcompose.ui.theme.Paragraph01
import com.afoxplus.uikitcompose.ui.theme.UiKitComposeTheme

@Composable
fun ToolbarComponent(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    onBackAction: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            modifier = modifier.size(44.dp),
            onClick = { onBackAction() }
        ) {
            Icon(
                modifier = modifier.size(24.dp),
                painter = painterResource(id = R.drawable.uikit_icon_back),
                contentDescription = "uikit_icon_back",
                tint = Dark01
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column(
            modifier = modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = title,
                color = Dark02,
                style = Paragraph01
            )
            Text(
                text = description,
                color = Dark01,
                style = Header04Bold
            )

        }

    }
}

@Preview
@Composable
fun ToolbarComponentPreview() {
    UiKitComposeTheme {
        ToolbarComponent(
            title = "client",
            description = "Juan Carlos del Rio"
        )
    }
}