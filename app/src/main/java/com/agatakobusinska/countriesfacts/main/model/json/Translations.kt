package com.agatakobusinska.countriesfacts.main.model.json

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Translations(

    @Json(name = "de")
    var de: String? = null,

    @Json(name = "es")
    var es: String? = null,

    @Json(name = "fr")
    var fr: String? = null,

    @Json(name = "ja")
    var ja: String? = null,

    @Json(name = "it")
    var it: String? = null,

    @Json(name = "br")
    var br: String? = null,

    @Json(name = "pt")
    var pt: String? = null
)