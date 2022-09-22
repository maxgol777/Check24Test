package com.test.check24testapp.di

import android.content.Context
import android.content.SharedPreferences
import com.test.check24testapp.feature_products.data.remote.ProductsApi
import com.test.check24testapp.feature_products.data.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideNewsApi(): ProductsApi {
        val interceptor = HttpLoggingInterceptor()
        interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }
        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create(ProductsApi::class.java)
    }

    @Provides
    @Singleton
    fun providePreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences("MyPref", 0)
}