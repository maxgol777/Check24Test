package com.test.check24testapp.feature_products.presentation.screens.products.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.test.check24testapp.feature_products.domain.products.Product


@Composable
fun ProductRow(
    product: Product,
    navController: DestinationsNavigator
) {
    Card(
        Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable {
                //navController.navigate(DetailScreenDestination(article.url))
            },
        shape = RoundedCornerShape(corner = CornerSize(3.dp)),
        elevation = 5.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (product.available) {
                ProductStateImage(imageUrl = product.imageURL)
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    modifier = Modifier.padding(start = 5.dp),
                    text = product.name,
                    fontSize = 20.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = product.description)
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = "${product.price.value} ${product.price.currency}")

            }
            if (!product.available) {
                ProductStateImage(imageUrl = product.imageURL)
            }
        }
    }
}

@Composable
fun ProductStateImage(imageUrl: String?) {
    imageUrl?.let {
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current).data(it)
                    .apply(block = fun ImageRequest.Builder.() {
                        crossfade(true)
                        transformations(CircleCropTransformation())
                    }).build()
            ),
            contentDescription = " icon image",
            modifier = Modifier.size(80.dp),
        )
    }
}