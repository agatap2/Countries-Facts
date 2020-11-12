package com.agatakobusinska.countriesfacts.main.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agatakobusinska.countriesfacts.main.model.entities.Country
import com.agatakobusinska.countriesfacts.main.model.repository.CountryRepository
import com.agatakobusinska.countriesfacts.main.utils.SearchingDataType
import com.agatakobusinska.countriesfacts.main.utils.SearchingDataType.CURRENCY
import com.agatakobusinska.countriesfacts.main.utils.SearchingDataType.NAME
import kotlinx.coroutines.launch

class CountryViewModel @ViewModelInject constructor(private val repository: CountryRepository) :
    ViewModel() {

    var country: MutableLiveData<Country> = MutableLiveData(Country())
    var countries: MutableLiveData<List<Country>> = MutableLiveData(listOf())

    fun updateCountry(id: Int) {
        viewModelScope.launch {
            country.postValue(repository.getCountryById(id))
        }
    }

    fun updateCountriesList(type: SearchingDataType, intSearchingCondition: Int = 0, stringSearchingCondition: String = "") {
        viewModelScope.launch {
            println("CONDITION: $stringSearchingCondition")
            when(type) {
                NAME -> countries.value = repository.getCountriesByName(stringSearchingCondition).value
                CURRENCY -> countries.value = repository.getCountriesByCurrency(stringSearchingCondition).value
                else -> countries.value = repository.getCountriesByName(stringSearchingCondition).value
            }
        }
    }
}