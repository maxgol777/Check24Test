package com.test.check24testapp.feature_products.presentation.screens.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.test.check24testapp.R
import com.test.check24testapp.feature_products.domain.products.Product
import com.test.check24testapp.feature_products.presentation.screens.components.ProductRow

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
@Destination

fun DetailScreen(
    product: Product? = null
) {
    product?.let {
        val scaffoldState = rememberScaffoldState()
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
            Column(modifier = Modifier.fillMaxSize()) {
                ProductRow(product = product)
                Spacer(modifier = Modifier.padding(5.dp))
                Text(text = product.longDescription)
            }
        }
    }
}