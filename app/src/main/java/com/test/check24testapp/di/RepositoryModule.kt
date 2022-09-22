package com.test.check24testapp.di

import com.test.check24testapp.feature_products.data.repository.ProductsRepositoryImpl
import com.test.check24testapp.feature_products.domain.repository.ProductsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindNewsRepository(
        productsRepositoryImpl: ProductsRepositoryImpl
    ): ProductsRepository
}