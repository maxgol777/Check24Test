package com.test.check24testapp.feature_products.presentation.screens.products

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.check24testapp.feature_products.domain.products.Header
import com.test.check24testapp.feature_products.domain.products.Product
import com.test.check24testapp.feature_products.domain.products.Products
import com.test.check24testapp.feature_products.domain.repository.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val repository: ProductsRepository
) : ViewModel() {

    var state by mutableStateOf(ScreenState())
        private set

    var request: suspend () -> Result<Products> = { repository.getProducts() }

    init {
        viewModelScope.launch {
            requestAction()
        }
    }

    private suspend fun requestAction() {
        state = state.copy(isLoading = true)
        request().mapCatching {
            state = state.copy(
                header = it.header,
                items = it.products,
                isLoading = false
            )
        }
    }

    fun loadItems() {
        state = state.copy(
            isLoading = true,
            header = Header("", ""),
            items = listOf()
        )
        viewModelScope.launch {
            requestAction()
        }
    }

    fun createNewRequest() {
        state = ScreenState()
        request = { repository.getProducts() }
        loadItems()
    }

    companion object {
        const val DEFAULT_PAGE_SIZE = 10
    }
}

data class ScreenState(
    val isLoading: Boolean = false,
    val header: Header = Header("", ""),
    val items: List<Product> = emptyList(),
    val filter: Filter = Filter1
)

sealed class Filter
object Filter1 : Filter()
object Filter2 : Filter()
object Filter3 : Filter()