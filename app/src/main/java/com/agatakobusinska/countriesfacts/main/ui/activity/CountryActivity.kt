package com.agatakobusinska.countriesfacts.main.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.agatakobusinska.countriesfacts.R
import com.agatakobusinska.countriesfacts.main.model.entities.Country
import com.agatakobusinska.countriesfacts.main.model.repository.CountryRepository
import com.agatakobusinska.countriesfacts.main.utils.loadImage
import com.agatakobusinska.countriesfacts.main.viewmodel.CountryViewModel
import com.agatakobusinska.countriesfacts.main.viewmodel.CountryViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_country.*
import java.text.NumberFormat
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class CountryActivity : AppCompatActivity() {

    @Inject
    lateinit var repository: CountryRepository
    private lateinit var viewModel: CountryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country)

        val id = Integer.parseInt(intent.getStringExtra("id"))
        viewModel = ViewModelProvider(
            this,
            CountryViewModelFactory(repository, id)
        ).get(CountryViewModel::class.java)

        val numberFormat = NumberFormat.getInstance(Locale.getDefault())
        numberFormat.isGroupingUsed = true

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.country.observe(this, { country ->

            supportActionBar?.title = country.name

            var languages = "-"
            var regionalBlocks = "-"
            var currencies = "-"
            var callingCodes = "-"
            var timeZones = "-"
            var borders = "-"

            country.languages?.forEach { item ->
                languages += "${item.languageName}, "
                languages = languages.substringAfter("-")
            }

            country?.borders?.forEach { item ->
                borders += "${item}, "
                borders = borders.substringAfter("-")
            }

            country?.regionalBlocs?.forEach { item ->
                regionalBlocks += "${item.regionalBlockName}, "
                regionalBlocks = regionalBlocks.substringAfter("-")
            }

            country?.currencies?.forEach { item ->
                currencies += item.currencyName ?: ""
                if (item.currencySymbol.isNullOrBlank().not())
                    currencies += " (${item.currencySymbol ?: ""})"
                currencies += ", "
                currencies = currencies.substringAfter("-")
            }

            country?.callingCodes?.forEach { item ->
                callingCodes += "$item, "
                callingCodes = callingCodes.substringAfter("-")
            }

            country?.timezones?.forEach { item ->
                timeZones += "$item, "
                timeZones = timeZones.substringAfter("-")
            }

            county_name.text = if (country?.name.equals(country?.nativeName)) {
                resources.getString(R.string.country_name, country?.name?.substringBefore("("), "")
            } else {
                resources.getString(R.string.country_name, country?.name?.substringBefore("("), country?.nativeName)
            }

            country_flag.loadImage(country?.flag, this)
            country_region.text = country?.region ?: "-"
            country_capital.text = country?.capital ?: "-"
            country_language.text = languages.substringBeforeLast(",")
            country_borders.text = resources.getString(R.string.borders, borders.substringBeforeLast(","))
            country_regional_block.text = regionalBlocks.substringBeforeLast(",")
            country_currency.text = currencies.substringBeforeLast(",")
            country_calling_code.text = callingCodes.substringBeforeLast(",")
            country_time_zone.text = timeZones.substringBeforeLast(",")
            country_population.text = numberFormat.format(country?.population?.toLong() ?: 0)
        })
    }
}

