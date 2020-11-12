package com.agatakobusinska.countriesfacts.main.di

import com.agatakobusinska.countriesfacts.main.model.repository.CountryRepository
import com.agatakobusinska.countriesfacts.main.viewmodel.CountryViewModel
import com.agatakobusinska.countriesfacts.main.viewmodel.PageViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun providePageViewModel(repository: CountryRepository) = PageViewModel(repository)

    @Singleton
    @Provides
    fun provideCountryViewModel(repository: CountryRepository) = CountryViewModel(repository)

}