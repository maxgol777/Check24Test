package com.test.check24testapp.feature_products.presentation.screens.products

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.test.check24testapp.R
import com.test.check24testapp.feature_products.presentation.screens.components.ProductRow
import com.test.check24testapp.feature_products.presentation.screens.products.components.ErrorScreen
import com.test.check24testapp.feature_products.presentation.util.Constants.FooterUrl

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
@RootNavGraph(start = true)
@Destination
fun SearchScreen(
    navigator: DestinationsNavigator,
    productsViewModel: ProductsViewModel = hiltViewModel(),
) {
    val scaffoldState = rememberScaffoldState()
    val state = productsViewModel.state

    val isRefreshing = productsViewModel.state.isLoading
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_name),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                    )
                },
                backgroundColor = MaterialTheme.colors.primary
            )
        },
    ) {
        state.errorMessage?.let {
            ErrorScreen(it) {
                productsViewModel.loadItems()
            }
            return@Scaffold
        }
        Column(Modifier.fillMaxSize()) {
            SwipeRefresh(
                state = rememberSwipeRefreshState(isRefreshing),
                onRefresh = { productsViewModel.loadItems() },
            ) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {

                    item {

                        Text(text = state.header.headerTitle)
                    }
                    item {
                        Text(text = state.header.headerDescription)
                    }
                    items(state.items.size) { i ->
                        val product = state.items[i]
                        ProductRow(product, navigator)
                    }
                    item {
                        Spacer(modifier = Modifier.padding(10.dp))
                        Text(text = stringResource(R.string.footer_message),
                            modifier = Modifier.clickable {
                                navigator.navigate(
                                    com.test.check24testapp.feature_products.presentation.screens.destinations.FooterScreenDestination(
                                        FooterUrl
                                    )
                                )
                            })
                        Spacer(modifier = Modifier.padding(10.dp))
                    }
                }
            }

        }
    }
}