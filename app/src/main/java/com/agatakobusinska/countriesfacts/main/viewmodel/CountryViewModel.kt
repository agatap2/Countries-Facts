package com.agatakobusinska.countriesfacts.main.viewmodel

import androidx.lifecycle.ViewModel
import com.agatakobusinska.countriesfacts.main.model.repository.CountryRepository

class CountryViewModel constructor(repository: CountryRepository, id: Int) :
    ViewModel() {

    val country = repository.getCountryById(id)
}