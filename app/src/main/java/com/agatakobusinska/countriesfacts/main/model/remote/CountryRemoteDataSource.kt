package com.agatakobusinska.countriesfacts.main.model.remote

import javax.inject.Inject

class CountryRemoteDataSource @Inject constructor(private val countryService: CountryService) {

    suspend fun getAllCountries() = countryService.getAllCountries()
}