package com.agatakobusinska.countriesfacts.main.utils

import androidx.room.TypeConverter
import com.agatakobusinska.countriesfacts.main.model.json.Currency
import com.agatakobusinska.countriesfacts.main.model.json.Language
import com.agatakobusinska.countriesfacts.main.model.json.RegionalBlock
import com.agatakobusinska.countriesfacts.main.model.json.Translations
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

class Converter {

    private val gson: Gson = Gson()

    @TypeConverter
    fun stringToStringsList(data: String?): List<String?>? {

        if (data == null) return Collections.emptyList()

        val listType: Type? = object : TypeToken<List<String?>?>() {}.type

        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun stringsListToString(data: List<String?>?): String? {
        return gson.toJson(data)
    }

    @TypeConverter
    fun stringToCurrenciesList(data: String?): List<Currency?>? {

        if (data == null) return Collections.emptyList()

        val listType: Type? = object : TypeToken<List<Currency?>?>() {}.type

        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun currenciesListToString(currencies: List<Currency?>?): String? {
        return gson.toJson(currencies)
    }

    @TypeConverter
    fun stringToLanguagesList(data: String?): List<Language?>? {

        if (data == null) return Collections.emptyList()

        val listType: Type? = object : TypeToken<List<Language?>?>() {}.type

        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun languagesListToString(languages: List<Language?>?): String? {
        return gson.toJson(languages)
    }

    @TypeConverter
    fun stringToTranslations(data: String?): Translations? {

        return if (data == null) null
        else gson.fromJson(data, Translations::class.java)
    }

    @TypeConverter
    fun translationsToString(translations: Translations?): String? {
        return gson.toJson(translations)
    }

    @TypeConverter
    fun stringToRegionalBlocksList(data: String?): List<RegionalBlock?>? {

        if (data == null) return Collections.emptyList()

        val listType: Type? = object : TypeToken<List<RegionalBlock?>?>() {}.type

        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun regionalBlocksListToString(regionalBlocks: List<RegionalBlock?>?): String? {
        return gson.toJson(regionalBlocks)
    }
}