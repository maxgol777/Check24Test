package com.test.check24testapp.feature_products.presentation.screens.footer

import androidx.compose.runtime.Composable
import com.test.check24testapp.feature_products.presentation.screens.footer.components.WebPageViewer
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

