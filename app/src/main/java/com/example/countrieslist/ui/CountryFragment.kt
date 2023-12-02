package com.example.countrieslist.ui

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
        if (!isNetworkAvailable(requireContext())) {
            binding.progressBar.visibility = View.GONE
            showError("Unable to connect. Please check your internet connection and try again.")
            return
        }
        setupObserver()
        binding.apply {
            recyclerViewCountries.layoutManager = LinearLayoutManager(context)
            recyclerViewCountries.adapter = countryAdapter
        }
    }

    private fun setupObserver() {
        viewModel.countries.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is UIState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    countryAdapter.submitList(uiState.listOfCountries)
                }
                is UIState.Failure -> {
                    val userFriendlyErrorMessage = "Something went wrong. Please try again later."
                    showError(userFriendlyErrorMessage)
                    binding.progressBar.visibility = View.GONE
                }
                is UIState.Loading -> {
                    binding.progressBar.visibility = if (uiState.isLoading) View.VISIBLE else View.GONE
                }
            }
        }
    }

    private fun showError(message: String) {
        val errorMessageTextView: TextView = binding.tvErrorMessage
        errorMessageTextView.text = message
        errorMessageTextView.visibility = View.VISIBLE
    }

    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}
