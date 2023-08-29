package com.afoxplus.yalistoadmin.ui.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.afoxplus.uikitcompose.ui.theme.Dark01
import com.afoxplus.uikitcompose.ui.theme.Dark05
import com.afoxplus.uikitcompose.ui.theme.Header05SemiBold
import com.afoxplus.uikitcompose.ui.theme.Paragraph02
import com.afoxplus.yalistoadmin.ui.home.RestaurantEntity

@Composable
fun InfoBusinessComponent(restaurantEntity: RestaurantEntity) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
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
            model = restaurantEntity.image,
            contentDescription = restaurantEntity.name,
            contentScale = ContentScale.FillBounds
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
        ) {
            Text(text = restaurantEntity.description, style = Paragraph02, color = Dark05)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = restaurantEntity.name, style = Header05SemiBold, color = Dark01)
        }
    }
}
