package com.agatakobusinska.countriesfacts.main.model.json

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CountryData(

    @Json(name = "name")
    var name: String?,

    @Json(name = "topLevelDomain")
    var domain: List<String>?,

    @Json(name = "alpha2Code")
    var alpha2Code: String?,

    @Json(name = "alpha3Code")
    var alpha3Code: String?,

    @Json(name = "callingCodes")
    var callingCodes: List<String>?,

    @Json(name = "capital")
    var capital: String?,

    @Json(name = "altSpellings")
    var altSpellings: List<String>?,

    @Json(name = "region")
    var region: String?,

    @Json(name = "subregion")
    var subregion: String?,

    @Json(name = "population")
    var population: Int?,

    @Json(name = "latlng")
    var latlng: List<Double>?,

    @Json(name = "demonym")
    var demonym: String?,

    @Json(name = "area")
    var area: Double?,

    @Json(name = "gini")
    var gini: Double?,

    @Json(name = "timezones")
    var timezones: List<String>?,

    @Json(name = "borders")
    var borders: List<String>?,

    @Json(name = "nativeName")
    var nativeName: String?,

    @Json(name = "numericCode")
    var numericCode: String?,

    @Json(name = "currencies")
    var currencies: List<Currency>?,

    @Json(name = "languages")
    var languages: List<Language>?,

    @Json(name = "translations")
    var translations: Translations?,

    @Json(name = "flag")
    var flag: String?,

    @Json(name = "regionalBlocs")
    var regionalBlocs: List<RegionalBlock>?,

    @Json(name = "cioc")
    var cioc: String?
)