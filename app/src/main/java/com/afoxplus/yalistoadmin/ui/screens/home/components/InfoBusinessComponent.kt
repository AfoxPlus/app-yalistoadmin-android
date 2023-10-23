package com.afoxplus.yalistoadmin.ui.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.afoxplus.uikit.designsystem.theme.UIKitTheme
import com.afoxplus.yalistoadmin.domain.entities.Restaurant

@Composable
fun InfoBusinessComponent(restaurant: Restaurant) {
    Column {
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
                        elevation = 5.dp,
                        shape = RoundedCornerShape(8.dp),
                        clip = true
                    ),
                model = restaurant.image,
                contentDescription = restaurant.name,
                contentScale = ContentScale.FillBounds
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp)
            ) {
                Text(
                    text = restaurant.description,
                    style = UIKitTheme.typography.paragraph02,
                    color = UIKitTheme.colors.blueGray800
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = restaurant.name, style = UIKitTheme.typography.header05SemiBold)
            }
        }
        Divider(color = UIKitTheme.colors.gray100, thickness = UIKitTheme.spacing.spacing02)
    }
}
