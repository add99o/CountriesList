package com.example.countrieslist.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.countrieslist.R
import com.example.countrieslist.databinding.CountryItemBinding
import com.example.countrieslist.model.Country

class CountriesAdapter :
    ListAdapter<Country, CountriesAdapter.ViewHolder>(CountryItemCallback) {

    private object CountryItemCallback : DiffUtil.ItemCallback<Country>() {
        override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem.name == newItem.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            CountryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            ),
        )
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    class ViewHolder(private val itemBinding: CountryItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(country: Country) {
            val context = itemView.context
            itemBinding.apply {
                textViewName.text = context.getString(
                    R.string.country_name_and_region,
                    country.name,
                    country.region,
                )
                textViewCode.text = country.code
                textViewCapital.text = country.capital
            }
        }
    }
}
