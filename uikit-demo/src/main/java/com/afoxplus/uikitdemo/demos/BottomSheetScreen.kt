package com.afoxplus.uikitdemo.demos

import android.util.Log
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.afoxplus.uikitcompose.ui.components.BottomSheetComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetScreen() {
    val sheetState: SheetState = rememberModalBottomSheetState()
    var itemSelectedVO: BottomSheetContentVO? by remember { mutableStateOf(null) }
    var showBottomSheet: Boolean by remember { mutableStateOf(false) }
    val list: ArrayList<BottomSheetContentVO> by remember {
        mutableStateOf(
            arrayListOf(
                BottomSheetContentVO(0, "Pendiente", false),
                BottomSheetContentVO(1, "Proceso", true),
                BottomSheetContentVO(1, "Rechazado", false),
                BottomSheetContentVO(1, "Finalizado", false),
            )
        )
    }

    Button(onClick = {
        showBottomSheet = true
    }) {
        Text(text = "Show BottomSheet")
    }

    if (showBottomSheet) {
        BottomSheetComponent(
            list = list,
            title = "Elige un nuevo estado al pedido",
            description = {
                it.description
            },
            onClick = { itemSelected ->
                showBottomSheet = false
                list.map { itemVO ->
                    itemVO.check = false
                    if (itemVO.description == itemSelected.description) {
                        itemVO.check = true
                        itemSelectedVO = itemVO
                    }
                    itemVO
                }
                Log.d("TAG", "Here - BottomSheetComponentPreview: $itemSelected")
            },
            onDismiss = {
                showBottomSheet = false
                Log.d("TAG", "Here - BottomSheetComponentPreview")
            },
            showIcon = {
                it.check
            },
            sheetState = sheetState
        )
    }
}

@Preview
@Composable
fun BottomSheetScreenPreview() {
    BottomSheetScreen()
}

data class BottomSheetContentVO(
    var id: Int,
    var description: String,
    var check: Boolean = false
)