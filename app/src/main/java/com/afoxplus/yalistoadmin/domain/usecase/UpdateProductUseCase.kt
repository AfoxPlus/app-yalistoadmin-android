package com.afoxplus.yalistoadmin.domain.usecase

import com.afoxplus.yalistoadmin.commons.utils.ResultState
import com.afoxplus.yalistoadmin.domain.entities.Product
import com.afoxplus.yalistoadmin.domain.repository.ProductRepository
import javax.inject.Inject

class UpdateProductUseCase @Inject constructor(private val productRepository: ProductRepository) {
    suspend operator fun invoke(product: Product): ResultState<Boolean> {
        return productRepository.updateProduct(product)
    }
}
