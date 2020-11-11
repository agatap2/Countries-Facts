package com.agatakobusinska.countriesfacts.main.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.agatakobusinska.countriesfacts.main.model.entities.Country

@Dao
interface CountryDao {

    @Query("SELECT * FROM countries_table")
    fun getAllCountries(): LiveData<List<Country>>

    @Query("SELECT * FROM countries_table WHERE region LIKE :region")
    fun getCountriesFromRegion(region: String): LiveData<List<Country>>

    @Query("SELECT * FROM countries_table WHERE id LIKE :id LIMIT 1")
    fun getCountry(id: Int): LiveData<Country>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCountries(countries: List<Country>)
}