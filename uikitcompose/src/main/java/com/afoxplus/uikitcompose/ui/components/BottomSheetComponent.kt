package com.afoxplus.uikitcompose.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.afoxplus.uikitcompose.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> BottomSheetComponent(
    modifier: Modifier = Modifier,
    title: String,
    list: ArrayList<T>,
    description: (T) -> String,
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
            Text(text = "Elige un nuevo estado al pedido")
            LazyColumn(modifier = modifier.fillMaxWidth()) {
                items(list.size) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            modifier = Modifier
                                .background(color = MaterialTheme.colorScheme.surface.copy(alpha = .4f))
                                .clickable { onClick(list[it]) },
                            text = description(list[it])
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.check_icon),
                            contentDescription = "check_icon"
                        )
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
        sheetState = sheetState
    )

}

data class BottomSheetContentVO(
    val id: Int,
    val description: String,
    val check: Boolean = false
)