package com.example.weathertoday

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.activity_weather_data.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        weatherToday.text = "ABCD"

    }

    fun fetchWeather(view: android.view.View) {
        val pinCode = pinCode.editableText.toString()
        val countryCode = countryCode.editableText.toString()
        val language = Language.editableText.toString()
        val units = units.editableText.toString()

        val intent = Intent(this,weatherData::class.java)
        intent.putExtra(weatherData.pinCode,pinCode)
        intent.putExtra(weatherData.countryCode,countryCode)
        intent.putExtra(weatherData.language,language)
        intent.putExtra(weatherData.units,units)
        startActivity(intent)
    }
}