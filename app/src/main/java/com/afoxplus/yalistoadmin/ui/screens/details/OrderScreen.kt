package com.afoxplus.yalistoadmin.ui.screens.details

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.afoxplus.uikitcompose.ui.components.ButtonOutlineYaListoComponent
import com.afoxplus.uikitcompose.ui.components.ButtonYaListoComponent
import com.afoxplus.uikitcompose.ui.components.ToolbarComponent
import com.afoxplus.uikitcompose.ui.theme.Dark05
import com.afoxplus.uikitcompose.ui.theme.Header05SemiBold
import com.afoxplus.uikitcompose.ui.theme.Light01
import com.afoxplus.uikitcompose.ui.theme.Light04
import com.afoxplus.uikitcompose.ui.theme.Light06
import com.afoxplus.yalistoadmin.ui.screens.details.components.OrderDetailComponent
import com.afoxplus.yalistoadmin.ui.screens.details.components.OrderWhatsappContactComponent
import com.afoxplus.yalistoadmin.domain.entities.Client
import com.afoxplus.yalistoadmin.domain.entities.Order
import com.afoxplus.yalistoadmin.domain.entities.Product

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OrderStatusScreen(
    orderViewModel: OrderViewModel = hiltViewModel(),
    navController: NavController
) {
    // val order = navController.currentBackStackEntry?.savedStateHandle?.get<Order>(ORDER) ?: return
    val order = Order(
        id = "64a4f0e9f03e52399e481854",
        number = "#000001",
        date = "04 Jul 2023, 11:26 PM",
        state = "Proceso",
        stateCode = "PROGRESS",
        restaurant = "Kitchen",
        orderType = com.afoxplus.yalistoadmin.domain.entities.OrderType(
            code = "SALON",
            title = "SALON",
            description = "06"
        ),
        total = "S/ 66.80",
        client = Client(
            name = "Prueba",
            cel = "966998544",
            addressReference = "Simbal, calle j4 puerta 250"
        ),
        detail = listOf(
            Product(
                productId = "61a5a68c0c327b1d087ccdb3",
                title = "Pescado",
                description = "Pescado frito",
                productType = "",
                unitPrice = "S/ 18.20",
                quantity = 2,
                subTotal = "S/ 36.40",
                subDetail = arrayListOf()
            ),
            Product(
                productId = "61a5a68c0c327b1d087ccdb3",
                title = "Pescado",
                description = "Pescado frito",
                productType = "",
                unitPrice = "S/ 18.20",
                quantity = 2,
                subTotal = "S/ 36.40",
                subDetail = listOf()
            ),
            Product(
                productId = "02",
                title = "Ají de gallina",
                quantity = 2,
                description = "",
                unitPrice = "S/. 25.00",
                productType = "Menu",
                subTotal = "S/. 50.00",
                subDetail = listOf(
                    Product(
                        productId = "02",
                        title = "Papa a la huancaina",
                        quantity = 1,
                        description = "",
                        unitPrice = "",
                        productType = "",
                        subTotal = "",
                        subDetail = arrayListOf()
                    ),
                    Product(
                        productId = "03",
                        title = "Ensalada cocida",
                        quantity = 1,
                        description = "",
                        unitPrice = "",
                        productType = "",
                        subTotal = "",
                        subDetail = arrayListOf()
                    )
                )
            )
        )
    )

    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .background(color = Light04)
            .fillMaxHeight()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            ToolbarComponent(
                modifier = Modifier.background(color = Light01),
                title = "Client",
                description = order.client.name
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
                    .background(Light06)
                    // .verticalScroll(scrollState)
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                stickyHeader {
                    OrderWhatsappContactComponent(
                        phoneNumber = order.client.cel,
                        description = order.client.addressReference
                    )
                }
                item {
                    OrderDetailComponent(
                        modifier = Modifier
                            .padding(horizontal = 0.dp, vertical = 8.dp),
                        order = order
                    )
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(35.dp)
                    .background(color = Dark05),
                horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = order.state,
                    textAlign = TextAlign.Center,
                    color = Light01,
                    style = Header05SemiBold
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 24.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.Start
            ) {
                ButtonYaListoComponent(text = "Modificar estado") {
                }

                ButtonOutlineYaListoComponent(
                    text = "Imprimir"
                ) {
                }
            }
        }
    }
}
