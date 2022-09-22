package com.test.check24testapp.feature_products.data.remote

data class ProductDto(
    val available: Boolean,
    val color: String,
    val colorCode: String,
    val description: String,
    val id: Int,
    val imageURL: String,
    val longDescription: String,
    val name: String,
    val price: PriceDto,
    val rating: Double,
    val releaseDate: Int,
    val type: String
)