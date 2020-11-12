package com.agatakobusinska.countriesfacts.main.model


import androidx.room.Database
import androidx.room.RoomDatabase
import com.agatakobusinska.countriesfacts.main.model.entities.Country
import com.agatakobusinska.countriesfacts.main.model.entities.Currency
import com.agatakobusinska.countriesfacts.main.model.local.CountryDao

@Database(entities = [Country::class, Currency::class], version = 12, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val countryDao: CountryDao
}