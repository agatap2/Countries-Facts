package com.agatakobusinska.countriesfacts.main.model.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.agatakobusinska.countriesfacts.main.model.entities.Country
import com.agatakobusinska.countriesfacts.main.model.json.CountryData
import com.agatakobusinska.countriesfacts.main.model.local.CountryDao
import com.agatakobusinska.countriesfacts.main.model.remote.CountryRemoteDataSource
import javax.inject.Inject


class CountryRepository @Inject constructor(
    private val remoteDataSource: CountryRemoteDataSource,
    private val localDataSource: CountryDao
) {

    val allCountries = localDataSource.getAllCountries()

    suspend fun insertCountries(countries: List<Country>) {
        localDataSource.insertAllCountries(countries)
    }

    fun getAllCountriesFromRegion(region: String) : LiveData<List<Country>> {
        return localDataSource.getCountriesFromRegion(region)
    }

    fun getCountryById(id: Int): LiveData<Country> {
        return localDataSource.getCountry(id)
    }

    suspend fun downloadCountriesList() {
        try {
            val fullList = remoteDataSource.getAllCountries()
            val finalList = formatDataList(fullList)
            localDataSource.insertAllCountries(finalList)
        } catch (e: Exception) {
            Log.e("ApiCall: ", "${e.message}")
        }
    }

    private fun formatDataList(fullList: List<CountryData>): List<Country> {

        val finalList = mutableListOf<Country>()
        for (countryData: CountryData in fullList) {
            val country = Country(
                name = countryData.name,
                callingCodes = countryData.callingCodes,
                capital = countryData.capital,
                region = countryData.region,
                population = countryData.population,
                timezones = countryData.timezones,
                nativeName = countryData.nativeName,
                currencies = countryData.currencies,
                languages = countryData.languages,
                flag = countryData.flag,
                regionalBlocs = countryData.regionalBlocs,
                borders = countryData.borders
            )
            finalList.add(country)
        }

        return finalList
    }
}