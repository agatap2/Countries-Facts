package com.agatakobusinska.countriesfacts.main.ui.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.agatakobusinska.countriesfacts.R
import com.agatakobusinska.countriesfacts.main.ui.adapter.Continent.*
import com.agatakobusinska.countriesfacts.main.ui.fragment.PlaceholderFragment

private val TAB_TITLES = arrayOf(
    EUROPE.title,
    AMERICAS.title,
    ASIA.title,
    AFRICA.title,
    OCEANIA.title
)

enum class Continent(val title: Int, val index: Int) {
    EUROPE(R.string.europe, 1),
    AMERICAS(R.string.americas, 2),
    ASIA(R.string.asia, 3),
    AFRICA(R.string.africa, 4),
    OCEANIA(R.string.oceania, 5)
}

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return PlaceholderFragment.newInstance(position + 1)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return TAB_TITLES.size
    }
}