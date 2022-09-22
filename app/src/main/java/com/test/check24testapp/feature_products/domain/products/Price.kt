package com.test.check24testapp.feature_products.domain.products

import java.io.Serializable

data class Price(
    val currency: String,
    val value: Double
) : Serializable