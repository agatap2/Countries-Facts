package com.agatakobusinska.countriesfacts.main.di

import android.content.Context
import androidx.room.Room
import com.agatakobusinska.countriesfacts.main.model.AppDatabase
import com.agatakobusinska.countriesfacts.main.model.local.CountryDao
import com.agatakobusinska.countriesfacts.main.model.remote.CountryRemoteDataSource
import com.agatakobusinska.countriesfacts.main.model.repository.CountryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) : AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "countries_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideCountryDao(db: AppDatabase) = db.countryDao

    @Provides
    fun provideRepository(
        countryRemoteDataSource: CountryRemoteDataSource,
        countryDao: CountryDao
    ): CountryRepository = CountryRepository(countryRemoteDataSource, countryDao)
}