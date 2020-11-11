package com.agatakobusinska.countriesfacts.main.model.remote

import com.agatakobusinska.countriesfacts.main.model.json.CountryData
import retrofit2.http.GET

interface CountryService {

    @GET("all")
    suspend fun getAllCountries(): List<CountryData>
}