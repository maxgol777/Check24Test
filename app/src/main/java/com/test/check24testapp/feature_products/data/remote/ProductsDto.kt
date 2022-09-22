package com.test.check24testapp.feature_products.data.remote

data class ProductsDto(
    val filters: List<String>,
    val header: HeaderDto,
    val products: List<ProductDto>
)