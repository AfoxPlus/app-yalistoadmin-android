package com.afoxplus.uikitcompose.ui.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.reflect.KProperty1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> BottomSheetComponent(
    modifier: Modifier = Modifier,
    title: String,
    list: ArrayList<T>,
    atributo: String,
    onClick: (T) -> Unit,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(onDismissRequest = { onDismiss() }) {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(list.size) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onClick(list[it]) },
                    text = readInstanceProperty(list[it] as Any, atributo) ?: ""
                )
            }
        }
        Spacer(modifier = Modifier.height(70.dp))
    }
}

@Preview
@Composable
fun BottomSheetComponentPreview() {
    //BottomSheetComponent()
    /*BottomSheetComponent(list = arrayListOf("hola", "como", "estas"), onClick = {
        Log.d("TAG", "Here - BottomSheetComponentPreview: $it")
    })*/

}

@Suppress("UNCHECKED_CAST")
fun <R> readInstanceProperty(instance: Any, propertyName: String): R {
    val property = instance::class.members
        // don't cast here to <Any, R>, it would succeed silently
        .first { it.name == propertyName } as KProperty1<Any, *>
    // force a invalid cast exception if incorrect type here
    return property.get(instance) as R
}

data class BottomSheetContentVO(
    val id: Int,
    val description: String
)