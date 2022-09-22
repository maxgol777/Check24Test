package com.test.check24testapp.feature_products.presentation.screens.detail

import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.test.check24testapp.feature_products.domain.products.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(
    private val preferences: SharedPreferences
) : ViewModel() {
    var product: Product? = null
    private val favoriteProducts = HashSet<String>()
    var state by mutableStateOf(DetailedScreenState())
        private set

    init {
        preferences.getStringSet(FAVORITE_PRODUCTS_KEY, setOf())?.let {
            favoriteProducts.addAll(it)
        }
        product?.let { updateFavoriteState(it) }
    }

    private fun updateFavoriteState(product: Product) {
        state = state.copy(
            isFavorite = favoriteProducts.contains(product.id.toString())
        )
    }

    fun changeFavoriteState(product: Product) {
        val id = product.id.toString()
        if (favoriteProducts.contains(id)) {
            removeProductFromFavorite(id)
        } else {
            productToFavorite(id)
        }
        updateFavoriteState(product)
    }

    private fun productToFavorite(id: String) {
        favoriteProducts.add(id)
        preferences.edit().putStringSet(FAVORITE_PRODUCTS_KEY, favoriteProducts)
            .apply()
        state = state.copy(isFavorite = favoriteProducts.contains(id.toString()))
    }

    private fun removeProductFromFavorite(id: String) {
        favoriteProducts.remove(id)
        preferences.edit().putStringSet(FAVORITE_PRODUCTS_KEY, favoriteProducts)
            .apply()
        state = state.copy(isFavorite = favoriteProducts.contains(id.toString()))
    }


    companion object {
        const val FAVORITE_PRODUCTS_KEY = "favProducts"
    }
}

data class DetailedScreenState(
    val isFavorite: Boolean = false
)