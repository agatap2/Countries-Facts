package com.agatakobusinska.countriesfacts.main.ui.activity

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.agatakobusinska.countriesfacts.R
import com.agatakobusinska.countriesfacts.main.model.repository.CountryRepository
import com.agatakobusinska.countriesfacts.main.ui.adapter.CountriesListAdapter
import com.agatakobusinska.countriesfacts.main.ui.fragment.TextSearchingDialogFragment.Companion.SEARCHING_RESULT
import com.agatakobusinska.countriesfacts.main.ui.fragment.TextSearchingDialogFragment.Companion.SEARCHING_TYPE
import com.agatakobusinska.countriesfacts.main.utils.SearchingDataType
import com.agatakobusinska.countriesfacts.main.viewmodel.CountryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_searching_result.*
import javax.inject.Inject

@AndroidEntryPoint
class SearchingResultActivity : AppCompatActivity() {

    private lateinit var countriesListAdapter: CountriesListAdapter

    @Inject
    lateinit var repository: CountryRepository
    @Inject
    lateinit var viewModel: CountryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_searching_result)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.searching_results)

        val stringSearchingCondition = intent.getStringExtra(SEARCHING_RESULT) ?: ""
        val intSearchingCondition = intent.getIntExtra("", 0)
        val type = intent.getSerializableExtra(SEARCHING_TYPE) as SearchingDataType

        viewModel.updateCountriesList(type, intSearchingCondition, stringSearchingCondition)

        countriesListAdapter = CountriesListAdapter(this)
        countries_list.adapter = countriesListAdapter

        viewModel.countries.observe(this, { countries ->
            if (countries.isNullOrEmpty().not()) {
                progress_circular.visibility = View.GONE
                countriesListAdapter.setData(countries)
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when(item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
}