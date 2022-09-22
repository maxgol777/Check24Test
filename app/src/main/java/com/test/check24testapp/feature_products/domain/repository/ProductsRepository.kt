package com.test.check24testapp.feature_products.domain.repository

import com.test.check24testapp.feature_products.domain.products.Products

interface ProductsRepository {
    suspend fun getProducts(): Result<Products>
}