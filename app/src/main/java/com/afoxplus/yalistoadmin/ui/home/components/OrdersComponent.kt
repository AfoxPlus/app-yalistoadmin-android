package com.afoxplus.yalistoadmin.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.afoxplus.uikitcompose.ui.theme.Light01

@Composable
fun OrdersComponent(
    modifier: Modifier = Modifier
        .fillMaxSize()
        .background(color = Light01),
    listado: ArrayList<String> = arrayListOf("", "", "")
) {
    LazyColumn(
        modifier = modifier.padding(horizontal = 20.dp, vertical = 10.dp),
        verticalArrangement = Arrangement.Top
    ) {
        items(listado.size) {
            ItemOrderComponent()
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun OrdersComponentPreview() {
    OrdersComponent()
}
