package com.agatakobusinska.countriesfacts.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.agatakobusinska.countriesfacts.main.model.repository.CountryRepository

class CountryViewModelFactory(private val repository: CountryRepository, private val id: Int): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CountryViewModel(repository, id) as T
    }
}