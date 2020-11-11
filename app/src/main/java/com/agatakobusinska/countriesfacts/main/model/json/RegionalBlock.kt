package com.agatakobusinska.countriesfacts.main.model.json

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegionalBlock (

    @Json(name = "acronym")
    var acronym: String? = null,

    @Json(name = "name")
    var regionalBlockName: String? = null,

    @Json(name = "otherAcronyms")
    var otherRegionalBlockAcronyms: List<String>? = null,

    @Json(name = "otherNames")
    var otherRegionalBlockNames: List<String>? = null,
)