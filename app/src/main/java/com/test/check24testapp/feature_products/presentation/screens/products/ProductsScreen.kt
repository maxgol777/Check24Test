package com.test.check24testapp.feature_products.presentation.screens.products

import android.annotation.SuppressLint
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
import com.test.check24testapp.feature_products.presentation.screens.products.components.ProductRow

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
                        if (state.isLoading) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    }
                }
            }
        }
    }
}