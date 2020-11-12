package com.agatakobusinska.countriesfacts.main.ui.activity

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.agatakobusinska.countriesfacts.R
import com.agatakobusinska.countriesfacts.main.ui.adapter.SectionsPagerAdapter
import com.agatakobusinska.countriesfacts.main.ui.fragment.TextSearchingDialogFragment
import com.agatakobusinska.countriesfacts.main.utils.SearchingDataType.CURRENCY
import com.agatakobusinska.countriesfacts.main.utils.SearchingDataType.NAME
import com.agatakobusinska.countriesfacts.main.utils.ViewUtils
import com.agatakobusinska.countriesfacts.main.viewmodel.PageViewModel
import com.google.android.material.tabs.TabLayout
import com.leinardi.android.speeddial.SpeedDialActionItem
import com.leinardi.android.speeddial.SpeedDialView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var utils: ViewUtils = ViewUtils()
    @Inject lateinit var pageViewModel: PageViewModel
    private lateinit var dialog: TextSearchingDialogFragment

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null) {
            pageViewModel.downloadCountries()
        }

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        viewPager.offscreenPageLimit = 5
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

        initSpeedDial(true)
    }

    private fun initSpeedDial(addActionItems: Boolean) {
        if (addActionItems) {

            fab.addActionItem(
                SpeedDialActionItem.Builder(
                    R.id.fab_country_calling_code, R.drawable.baseline_call_24)
                    .setLabel(getString(R.string.by_calling_code))
                    .create()
            )

            fab.addActionItem(
                SpeedDialActionItem.Builder(
                    R.id.fab_country_currency,
                    R.drawable.baseline_euro_symbol_24
                )
                    .setLabel(R.string.by_currency)
                    .create()
            )

            fab.addActionItem(
                SpeedDialActionItem.Builder(
                    R.id.fab_country_language,
                    R.drawable.baseline_language_24
                )
                    .setLabel(R.string.by_language)
                    .create()
            )

            fab.addActionItem(
                SpeedDialActionItem.Builder(
                    R.id.fab_country_capital, R.drawable.baseline_location_city_24
                )
                    .setLabel(getString(R.string.by_capital))
                    .create()
            )

            fab.addActionItem(
                SpeedDialActionItem.Builder(
                    R.id.fab_country_name,
                    R.drawable.baseline_spellcheck_24
                )
                    .setLabel(R.string.by_name)
                    .create()
            )
        }

        fab.setOnActionSelectedListener(SpeedDialView.OnActionSelectedListener { actionItem ->
            when (actionItem.id) {
                R.id.fab_country_name -> {
                    dialog = TextSearchingDialogFragment.getInstance("Find country by name or code", NAME)
                    dialog.show(supportFragmentManager, "name")
                    return@OnActionSelectedListener true
                }
                R.id.fab_country_currency -> {
                    dialog = TextSearchingDialogFragment.getInstance("Find country by its currency name or code", CURRENCY)
                    dialog.show(supportFragmentManager, "currency")
                    return@OnActionSelectedListener true
                }
                R.id.fab_country_language -> {
                    utils.showToast(this, actionItem.getLabel(this@MainActivity) + " LANGUAGE.")
                    // closes without animation (same as fab.close(false); return false;)
                    return@OnActionSelectedListener false
                }
                R.id.fab_country_capital -> utils.showToast(
                    this,
                    actionItem.getLabel(this@MainActivity) + " CAPITAL!"
                )
                R.id.fab_country_calling_code -> utils.showToast(
                    this,
                    actionItem.getLabel(this@MainActivity) + " CALLING CODE!"
                )
            }
            true
        })
    }
}