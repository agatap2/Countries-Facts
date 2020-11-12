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

    private val values: MutableList<Country> = mutableListOf()
    private val onClickListener: View.OnClickListener
    private var selectedItem: Country? = null

    init {
        onClickListener = View.OnClickListener { view ->
            selectedItem = view.tag as Country
            val intent = Intent(view.context, CountryActivity::class.java).apply {
                putExtra("id", selectedItem?.id)
            }
            view.context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        return ViewHolder(view, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.updateWithCountry(values[position])
        holder.setIsRecyclable(false)

        with(holder.itemView) {
            tag = values[position]
            setOnClickListener(onClickListener)
        }
    }

    override fun getItemCount(): Int = values.size

    fun setData(countries: List<Country>) {
        println("RESULT: $countries")
        values.clear()
        values.addAll(countries.distinctBy { it.id })
        notifyDataSetChanged()
    }

    class ViewHolder(view: View, private val context: Context) : RecyclerView.ViewHolder(view) {
        private val name: TextView = view.county_name
        private val flag: ImageView = view.country_flag

        fun updateWithCountry(country: Country?) {
            name.text = country?.name?.substringBefore('(')
            flag.loadImage(country?.flag, context)
        }
    }
}

