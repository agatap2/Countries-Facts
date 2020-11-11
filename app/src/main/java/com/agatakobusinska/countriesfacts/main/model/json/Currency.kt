package com.agatakobusinska.countriesfacts.main.model.json

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Currency(

    @Json(name = "code")
    var currencyCode: String? = null,

    @Json(name = "name")
    var currencyName: String? = null,

    @Json(name = "symbol")
    var currencySymbol: String? = null
)