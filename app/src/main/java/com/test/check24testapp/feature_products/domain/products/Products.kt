package com.test.check24testapp.feature_products.domain.products

data class Products(
    val filters: List<String>,
    val header: Header,
    val products: List<Product>
)