package com.example.countrieslist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countrieslist.databinding.CountryFragmentLayoutBinding
import com.example.countrieslist.model.UIState
import com.example.countrieslist.viewmodel.CountriesViewModel

class CountryFragment : Fragment() {

    private lateinit var binding: CountryFragmentLayoutBinding
    private val viewModel: CountriesViewModel by lazy {
        ViewModelProvider(this)[CountriesViewModel::class.java]
    }
    private val countryAdapter: CountriesAdapter by lazy {
        CountriesAdapter()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = CountryFragmentLayoutBinding.inflate(
            inflater,
            container,
            false,
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupObserver()
        binding.apply {
            recyclerViewCountries.layoutManager = LinearLayoutManager(context)
            recyclerViewCountries.adapter = countryAdapter
        }
    }

    private fun setupObserver() {
        viewModel.countries.observe(viewLifecycleOwner) { countries ->
            when (countries) {
                is UIState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    countryAdapter.submitList(countries.listOfCountries)
                }
                is UIState.Failure -> {
                    Toast.makeText(context, countries.errorMessage, Toast.LENGTH_LONG).show()
                    binding.progressBar.visibility = View.GONE
                }
                is UIState.Loading -> {
                    activity?.runOnUiThread {
                        binding.progressBar.visibility =
                            if (countries.isLoading) View.VISIBLE else View.GONE
                    }
                }
            }
        }
    }
}
