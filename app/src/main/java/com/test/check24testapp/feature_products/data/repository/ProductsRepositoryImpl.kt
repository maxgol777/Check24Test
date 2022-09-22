package com.test.check24testapp.feature_products.data.repository

import android.util.Log
import com.test.check24testapp.feature_products.data.mappers.toProducts
import com.test.check24testapp.feature_products.data.remote.ProductsApi
import com.test.check24testapp.feature_products.domain.products.Products
import com.test.check24testapp.feature_products.domain.repository.ProductsRepository
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(private val api: ProductsApi) : ProductsRepository {
    override suspend fun getProducts(): Result<Products> = try {
        val articles = api.getProducts().toProducts()
        Result.success(articles)
    } catch (exception: Exception) {
        Log.d(TAG, "getNews: $exception")
        Result.failure(exception)
    }

    companion object {
        const val TAG = "ProductsRepository"
    }
}