package com.afoxplus.yalistoadmin.delivery.components.restaurant

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.afoxplus.uikit.designsystem.foundations.UIKitColorTheme
import com.afoxplus.uikit.designsystem.foundations.UIKitTheme
import com.afoxplus.yalistoadmin.domain.entities.Restaurant

@Composable
fun InfoBusinessComponent(restaurant: Restaurant) {
    Column(modifier = Modifier.background(UIKitColorTheme.light01)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(UIKitTheme.spacing.spacing16),
            horizontalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                modifier = Modifier
                    .width(80.dp)
                    .height(80.dp)
                    .shadow(
                        elevation = UIKitTheme.spacing.spacing04,
                        shape = UIKitTheme.shapes.small,
                        clip = true
                    ),
                model = restaurant.image,
                contentDescription = restaurant.name,
                contentScale = ContentScale.FillBounds
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(UIKitTheme.spacing.spacing18)
            ) {
                Text(
                    text = restaurant.description,
                    style = UIKitTheme.typography.paragraph02,
                    color = UIKitTheme.colors.gray500
                )
                Spacer(modifier = Modifier.height(UIKitTheme.spacing.spacing04))
                Text(text = restaurant.name, style = UIKitTheme.typography.header05SemiBold)
            }
        }
        HorizontalDivider(color = UIKitTheme.colors.gray100, thickness = UIKitTheme.spacing.spacing02)
    }
}
