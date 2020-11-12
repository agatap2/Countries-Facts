package com.agatakobusinska.countriesfacts.main.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.agatakobusinska.countriesfacts.main.model.json.Currency
import com.agatakobusinska.countriesfacts.main.model.json.Language
import com.agatakobusinska.countriesfacts.main.model.json.RegionalBlock
import com.agatakobusinska.countriesfacts.main.utils.Converter
import java.io.Serializable

@Entity(tableName = "countries_table")
@TypeConverters(Converter::class)
data class Country(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "country_name")
    var name: String? = "",

    @ColumnInfo(name = "country_calling_code")
    var callingCodes: List<String>? = emptyList(),

    @ColumnInfo(name = "capital")
    var capital: String? = "",

    @ColumnInfo(name = "region")
    var region: String? = "",

    @ColumnInfo(name = "population")
    var population: Int? = 0,

    @ColumnInfo(name = "time_zone")
    var timezones: List<String>? = emptyList(),

    @ColumnInfo(name = "native_name")
    var nativeName: String? = "",

    @ColumnInfo(name = "currencies")
    var currencies: List<Currency>? = emptyList(),

    @ColumnInfo(name = "currency_id")
    var currencyId: Int? = 0,

    @ColumnInfo(name = "languages")
    var languages: List<Language>? = emptyList(),

    @ColumnInfo(name = "country_flag")
    var flag: String? = "",

    @ColumnInfo(name = "regional_block")
    var regionalBlocs: List<RegionalBlock>? = emptyList(),

    @ColumnInfo(name = "borders")
    var borders: List<String>? = emptyList(),

    @ColumnInfo(name = "alpha3code")
    var alpha3Code: String? = ""

) : Serializable