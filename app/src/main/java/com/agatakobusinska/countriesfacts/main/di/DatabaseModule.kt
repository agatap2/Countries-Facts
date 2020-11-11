package com.agatakobusinska.countriesfacts.main.di

import android.content.Context
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
    fun provideDatabase(@ApplicationContext context: Context) = AppDatabase.getDatabase(context)

    @Singleton
    @Provides
    fun provideCountryDao(db: AppDatabase) = db.countryDao()

    @Singleton
    @Provides
    fun proviceRepository(
        countryRemoteDataSource: CountryRemoteDataSource,
        countryDao: CountryDao
    ): CountryRepository = CountryRepository(countryRemoteDataSource, countryDao)
}