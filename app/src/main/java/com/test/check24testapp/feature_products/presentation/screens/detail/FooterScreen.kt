package com.test.check24testapp.feature_products.presentation.screens.detail

import androidx.compose.runtime.Composable
import com.example.newstestapp.feature_news.presentation.screens.detail.components.WebPageViewer
import com.ramcosta.composedestinations.annotation.Destination

@Composable
@Destination
fun FooterScreen(
    url: String? = null
) {
    url?.let {
        WebPageViewer(url = url)
    }
}

