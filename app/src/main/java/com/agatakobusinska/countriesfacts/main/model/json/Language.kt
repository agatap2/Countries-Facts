package com.agatakobusinska.countriesfacts.main.model.json

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Language(

    @Json(name = "iso639_1")
    var iso6391: String? = null,

    @Json(name = "iso639_2")
    var iso6392: String? = null,

    @Json(name = "name")
    var languageName: String? = null,

    @Json(name = "nativeName")
    var languageNativeName: String? = null,
)