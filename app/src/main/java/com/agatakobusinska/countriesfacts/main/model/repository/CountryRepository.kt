package com.agatakobusinska.countriesfacts.main.model.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.agatakobusinska.countriesfacts.main.model.entities.Country
import com.agatakobusinska.countriesfacts.main.model.json.CountryData
import com.agatakobusinska.countriesfacts.main.model.local.CountryDao
import com.agatakobusinska.countriesfacts.main.model.remote.CountryRemoteDataSource
import com.agatakobusinska.countriesfacts.main.model.entities.Currency as EntitiesCurrency


class CountryRepository(
    private val remoteDataSource: CountryRemoteDataSource,
    private val localDataSource: CountryDao
) {

    fun getAllCountriesFromRegion(region: String): LiveData<List<Country>> {
        return localDataSource.getCountriesFromRegion(region)
    }

    suspend fun getCountryById(id: Int): Country {
        return localDataSource.getCountryById(id)
    }

    fun getCountriesByName(name: String): LiveData<List<Country>> {
        return localDataSource.getCountryByName("%$name%")
    }

    fun getCountriesByCurrency(currency: String): LiveData<List<Country>> {
        return localDataSource.getCountriesByCurrency(currency)
    }

    suspend fun downloadCountriesList() {
        try {
            val fullList = remoteDataSource.getAllCountries()
            val finalList = formatDataList(fullList)
            localDataSource.insertAllCountries(finalList.first)
            localDataSource.insertAllCurrencies(finalList.second)
        } catch (e: Exception) {
            Log.e("ApiCall: ", "${e.message}")
        }
    }

    private fun formatDataList(fullList: List<CountryData>): Pair<List<Country>, List<EntitiesCurrency>> {

        val finalCountryList = mutableListOf<Country>()
        val finalCurrencyList = mutableListOf<EntitiesCurrency>()
        var countryCurrencyRelationId = 0
        fullList.forEach { countryData: CountryData ->
            countryCurrencyRelationId++
            val country = Country(
                name = countryData.name,
                callingCodes = countryData.callingCodes,
                capital = countryData.capital,
                region = countryData.region,
                population = countryData.population,
                timezones = countryData.timezones,
                nativeName = countryData.nativeName,
                currencies = countryData.currencies,
                currencyId = countryCurrencyRelationId,
                languages = countryData.languages,
                flag = countryData.flag,
                regionalBlocs = countryData.regionalBlocs,
                borders = countryData.borders,
                alpha3Code = countryData.alpha3Code
            )
            finalCountryList.add(country)
            country.currencies?.forEach { currencyData ->
                val currency = EntitiesCurrency(
                    countryId = countryCurrencyRelationId,
                    currencyCode = currencyData.currencyCode,
                    currencyName = currencyData.currencyName,
                    currencySymbol = currencyData.currencySymbol
                )
                finalCurrencyList.add(currency)
            }
        }
        println(finalCountryList)
        return Pair(finalCountryList, finalCurrencyList)
    }
}