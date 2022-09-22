package com.test.check24testapp.feature_products.data.remote

import retrofit2.http.GET

interface ProductsApi {
    @GET(value = "products-test.json")
    suspend fun getProducts(): ProductsDto
}