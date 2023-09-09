package com.afoxplus.yalistoadmin.ui.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.afoxplus.uikitcompose.ui.components.ButtonOutlineYaListoComponent
import com.afoxplus.uikitcompose.ui.components.ButtonYaListoComponent
import com.afoxplus.uikitcompose.ui.components.ToolbarComponent
import com.afoxplus.uikitcompose.ui.theme.Dark05
import com.afoxplus.uikitcompose.ui.theme.Header05SemiBold
import com.afoxplus.uikitcompose.ui.theme.Light01
import com.afoxplus.uikitcompose.ui.theme.Light06
import com.afoxplus.yalistoadmin.ui.screens.details.components.OrderDetailComponent
import com.afoxplus.yalistoadmin.ui.screens.details.components.OrderWhatsappContactComponent

@Preview(showBackground = true)
@Composable
fun OrderStatusScreen(orderViewModel: OrderViewModel = hiltViewModel()) {
    Surface {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            ToolbarComponent(title = "Client", description = "Juan Carlos del Rio")
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Light06)
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                OrderWhatsappContactComponent(
                    phoneNumber = "9669985444",
                    description = "Cerca al parque los jardines"
                )
                OrderDetailComponent(
                    modifier = Modifier
                        .padding(horizontal = 0.dp, vertical = 8.dp)
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
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
                    text = "Pendiente",
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
