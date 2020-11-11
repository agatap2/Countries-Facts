package com.agatakobusinska.countriesfacts.main.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.agatakobusinska.countriesfacts.R
import com.agatakobusinska.countriesfacts.main.model.entities.Country
import com.agatakobusinska.countriesfacts.main.ui.activity.CountryActivity
import com.agatakobusinska.countriesfacts.main.utils.loadImage
import kotlinx.android.synthetic.main.list_item.view.*


class CountriesListAdapter(private val context: Context) :
    RecyclerView.Adapter<CountriesListAdapter.ViewHolder>() {

    private val countries: ArrayList<Country> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        view.setOnClickListener {
            val intent = Intent(it.context, CountryActivity::class.java).apply {
                putExtra("id", it.country_id.text)
            }
            it.context.startActivity(intent)
        }

        return ViewHolder(view, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.updateWithCountry(countries[position])
    }

    override fun getItemCount(): Int = countries.size

    fun setData(countries: List<Country>) {
        this.countries.clear()
        this.countries.addAll(countries)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View, private val context: Context) : RecyclerView.ViewHolder(view) {
        private val name: TextView = view.county_name
        private val flag: ImageView = view.country_flag
        private val id: TextView = view.country_id

        fun updateWithCountry(country: Country?) {
            name.text = country?.name?.substringBefore('(')
            flag.loadImage(country?.flag, context)
            id.text = country?.id.toString()
        }
    }
}

