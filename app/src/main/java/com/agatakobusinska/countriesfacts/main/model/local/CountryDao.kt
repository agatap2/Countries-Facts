package com.agatakobusinska.countriesfacts.main.model.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.agatakobusinska.countriesfacts.main.model.entities.Country
import com.agatakobusinska.countriesfacts.main.model.entities.Currency

@Dao
interface CountryDao {

    @Query("SELECT * FROM countries_table")
    fun getAllCountries(): LiveData<List<Country>>

    @Query("SELECT * FROM countries_table WHERE region LIKE :region")
    fun getCountriesFromRegion(region: String): LiveData<List<Country>>

    @Query("SELECT * FROM countries_table WHERE id LIKE :id LIMIT 1")
    suspend fun getCountryById(id: Int): Country

    @Query("SELECT * FROM countries_table WHERE country_name LIKE :name OR native_name LIKE :name OR alpha3code LIKE :name")
    fun getCountryByName(name: String): LiveData<List<Country>>

    @Query("SELECT * FROM countries_table INNER JOIN currency_table " +
            "ON currency_table.country_id = countries_table.currency_id " +
            "WHERE currency_table.code LIKE :currency OR currency_table.name " +
            "LIKE :currency OR currency_table.symbol LIKE :currency")
    fun getCountriesByCurrency(currency: String): LiveData<List<Country>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCountries(countries: List<Country>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCurrencies(currency: List<Currency>)
}