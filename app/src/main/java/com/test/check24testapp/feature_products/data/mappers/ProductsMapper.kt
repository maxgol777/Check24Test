package com.test.check24testapp.feature_products.data.mappers

import com.test.check24testapp.feature_products.data.remote.HeaderDto
import com.test.check24testapp.feature_products.data.remote.PriceDto
import com.test.check24testapp.feature_products.data.remote.ProductDto
import com.test.check24testapp.feature_products.data.remote.ProductsDto
import com.test.check24testapp.feature_products.domain.products.Header
import com.test.check24testapp.feature_products.domain.products.Price
import com.test.check24testapp.feature_products.domain.products.Product
import com.test.check24testapp.feature_products.domain.products.Products

fun ProductsDto.toProducts(): Products = Products(
    filters = filters,
    header = header.toHeader(),
    products = products.map { it.toProduct() }
)

fun HeaderDto.toHeader(): Header = Header(
    headerDescription = headerDescription,
    headerTitle = headerTitle
)

fun ProductDto.toProduct(): Product = Product(
    available = available,
    color = color,
    colorCode = colorCode,
    description = description,
    id = id,
    imageURL = imageURL,
    longDescription = longDescription,
    name = name,
    price = price.toPrice(),
    rating = rating,
    releaseDate = releaseDate,
    type = type
)

fun PriceDto.toPrice(): Price = Price(
    currency = currency,
    value = value
)