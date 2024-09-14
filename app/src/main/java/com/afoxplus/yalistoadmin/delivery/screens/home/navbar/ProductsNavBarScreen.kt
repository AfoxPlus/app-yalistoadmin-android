package com.afoxplus.yalistoadmin.delivery.screens.home.navbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.afoxplus.uikit.designsystem.foundations.UIKitTheme
import com.afoxplus.uikit.designsystem.molecules.UIKitLoading
import com.afoxplus.yalistoadmin.delivery.viewmodels.ProductViewModel
import com.afoxplus.yalistoadmin.domain.entities.Product

internal typealias ProductSwitchClicked = (product: Product) -> Unit

@Composable
fun ProductScreen(
    productViewModel: ProductViewModel = hiltViewModel()
) {
    val result by productViewModel.productResult.collectAsStateWithLifecycle()
    when (result) {
        is ProductViewModel.ProductResult.Loading -> HandleShowLoading()
        is ProductViewModel.ProductResult.Success -> {
            val products = (result as ProductViewModel.ProductResult.Success).data
            HandleShowProducts(listProducts = products) { product ->
                productViewModel.updateShowInAppProduct(product)
            }
        }

        is ProductViewModel.ProductResult.Error -> HandleShowError((result as ProductViewModel.ProductResult.Error).error)
    }
    LaunchedEffect(key1 = Unit) { productViewModel.searchProducts() }
}

@Composable
fun HandleShowError(ex: Throwable) {
    // Handle Show Error
}

@Composable
fun HandleShowLoading() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UIKitLoading(circleSize = 12.dp)
    }
}

@Composable
fun HandleShowProducts(listProducts: List<Product>, productSwitchClicked: ProductSwitchClicked) {
    val lasItemIndex = listProducts.lastIndex
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        itemsIndexed(listProducts) { index, product ->
            val showDividerLine = hasDividerLine(lasItemIndex, index)
            ProductIterator(
                product = product,
                showDividerLine = showDividerLine,
                productSwitchClicked
            )
        }
    }
}

private fun hasDividerLine(lastItemIndex: Int, currentItemIndex: Int) =
    lastItemIndex != currentItemIndex

@Composable
fun ProductIterator(
    product: Product,
    showDividerLine: Boolean = true,
    productSwitchClicked: ProductSwitchClicked
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        ItemProduct(product = product, productSwitchClicked = productSwitchClicked)
        if (showDividerLine) {
            HorizontalDivider(color = UIKitTheme.colors.gray100)
        }
    }
}

@Composable
fun ItemProduct(product: Product, productSwitchClicked: ProductSwitchClicked) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier
                .width(64.dp)
                .height(64.dp),
            model = product.imageUrl,
            contentDescription = product.title,
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(2.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = product.productType.toString(),
                style = UIKitTheme.typography.paragraph02Bold
            )
            Text(text = product.title, style = UIKitTheme.typography.header05SemiBold)
            Text(text = product.description.toString(), style = UIKitTheme.typography.paragraph02)
            Text(text = product.unitPrice.toString(), style = UIKitTheme.typography.header05Bold)
        }
        var checkState by remember { mutableStateOf(product.showInApp) }
        Switch(
            checked = checkState,
            colors = SwitchDefaults.colors(
                checkedThumbColor = UIKitTheme.colors.light01,
                checkedTrackColor = UIKitTheme.colors.green400,
                uncheckedThumbColor = UIKitTheme.colors.light01,
                uncheckedTrackColor = UIKitTheme.colors.gray100,
                uncheckedBorderColor = UIKitTheme.colors.gray300
            ),
            onCheckedChange = {
                checkState = it
                product.showInApp = checkState
                productSwitchClicked(product)
            }
        )
    }
}
