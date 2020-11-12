package com.agatakobusinska.countriesfacts.main.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agatakobusinska.countriesfacts.main.model.entities.Country
import com.agatakobusinska.countriesfacts.main.model.repository.CountryRepository
import kotlinx.coroutines.launch

class PageViewModel @ViewModelInject constructor(
    private val repository: CountryRepository
) : ViewModel() {

    val europe: LiveData<List<Country>> = repository.getAllCountriesFromRegion("europe")
    val americas: LiveData<List<Country>> = repository.getAllCountriesFromRegion("americas")
    val asia: LiveData<List<Country>> = repository.getAllCountriesFromRegion("asia")
    val africa: LiveData<List<Country>> = repository.getAllCountriesFromRegion("africa")
    val oceania: LiveData<List<Country>> = repository.getAllCountriesFromRegion("oceania")

    fun downloadCountries() = viewModelScope.launch {
        repository.downloadCountriesList()
    }
}