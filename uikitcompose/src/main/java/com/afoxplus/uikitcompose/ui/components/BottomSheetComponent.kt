package com.afoxplus.uikitcompose.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.afoxplus.uikitcompose.R
import com.afoxplus.uikitcompose.ui.extensions.noRippleClickable
import com.afoxplus.uikitcompose.ui.theme.Dark01
import com.afoxplus.uikitcompose.ui.theme.Dark03
import com.afoxplus.uikitcompose.ui.theme.Header05SemiBold
import com.afoxplus.uikitcompose.ui.theme.Light03
import com.afoxplus.uikitcompose.ui.theme.Paragraph01SemiBold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> BottomSheetComponent(
    modifier: Modifier = Modifier,
    title: String,
    list: MutableList<T>,
    description: (T) -> String,
    showIcon: (T) -> Boolean,
    onClick: (T) -> Unit,
    onDismiss: () -> Unit,
    sheetState: SheetState,
    color: Color = Color.White
) {
    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = sheetState,
        containerColor = color
    ) {
        Column {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = title,
                style = Header05SemiBold,
                color = Dark01,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(10.dp))
            Divider(
                modifier = Modifier
                    .height(2.dp)
                    .background(color = Light03)
            )
            Spacer(modifier = Modifier.height(10.dp))
            LazyColumn(modifier = modifier.fillMaxWidth()) {
                items(list.size) { itemPosition ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp)
                            .noRippleClickable {
                                onClick(list[itemPosition])
                            }
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = description(list[itemPosition]),
                                style = Paragraph01SemiBold,
                                color = Dark03
                            )
                            if (showIcon(list[itemPosition])) {
                                Icon(
                                    painter = painterResource(id = R.drawable.check_icon),
                                    contentDescription = "check_icon"
                                )
                            }
                        }
                        if ((list.size - 1) != itemPosition) {
                            Spacer(modifier = Modifier.height(8.dp))
                            Divider(
                                modifier = Modifier
                                    .height(1.dp)
                                    .background(color = Light03)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }

                }
            }
        }
        Spacer(modifier = Modifier.height(70.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun BottomSheetComponentPreview() {
    val sheetState = rememberModalBottomSheetState()
    BottomSheetComponent(
        list = arrayListOf(
            BottomSheetContentVO(0, "Pendiente", false),
            BottomSheetContentVO(1, "Proceso", true),
            BottomSheetContentVO(1, "Rechazado", false),
            BottomSheetContentVO(1, "Finalizado", false),
        ),
        title = "Elige un nuevo estado al pedido",
        description = {
            it.description
        },
        onClick = {
            Log.d("TAG", "Here - BottomSheetComponentPreview: $it")
        },
        onDismiss = {
            Log.d("TAG", "Here - BottomSheetComponentPreview")
        },
        showIcon = {
            it.check
        },
        sheetState = sheetState
    )
}

data class BottomSheetContentVO(
    val id: Int,
    val description: String,
    val check: Boolean = false
)