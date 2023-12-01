package com.example.countrieslist.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.countrieslist.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val countryFragment = CountryFragment()
            countryFragment.retainInstance = true
            supportFragmentManager.beginTransaction()
                .replace(R.id.country_fragment_container, countryFragment)
                .commit()
        }
    }
}
