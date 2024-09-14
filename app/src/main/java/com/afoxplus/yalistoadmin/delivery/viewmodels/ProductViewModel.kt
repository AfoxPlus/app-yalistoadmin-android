package com.afoxplus.yalistoadmin.delivery.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.afoxplus.uikit.di.UIKitCoroutineDispatcher
import com.afoxplus.yalistoadmin.cross.utils.ResultState
import com.afoxplus.yalistoadmin.domain.entities.Product
import com.afoxplus.yalistoadmin.domain.usecase.GetProductsByRestaurantUseCase
import com.afoxplus.yalistoadmin.domain.usecase.UpdateProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getProductsByRestaurantUseCase: GetProductsByRestaurantUseCase,
    private val updateProductUseCase: UpdateProductUseCase,
    private val dispatcher: UIKitCoroutineDispatcher
) : ViewModel() {

    private val mProductResult: MutableStateFlow<ProductResult> by lazy {
        MutableStateFlow(ProductResult.Loading)
    }
    val productResult = mProductResult.asStateFlow()

    fun searchProducts() = viewModelScope.launch(dispatcher.getIODispatcher()) {
        when (val result = getProductsByRestaurantUseCase()) {
            is ResultState.Error -> mProductResult.value = ProductResult.Error(result.exception)
            is ResultState.Success -> mProductResult.value = ProductResult.Success(result.data)
        }
    }

    fun updateShowInAppProduct(product: Product) =
        viewModelScope.launch(dispatcher.getIODispatcher()) {
            updateProductUseCase(product)
        }

    sealed interface ProductResult {
        object Loading : ProductResult
        data class Success(val data: List<Product>) : ProductResult
        data class Error(val error: Throwable) : ProductResult
    }
}
