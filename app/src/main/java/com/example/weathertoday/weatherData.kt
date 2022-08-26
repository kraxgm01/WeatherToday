package com.example.weathertoday

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Request.Method.GET
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_weather_data.*
import org.json.JSONArray


class weatherData : AppCompatActivity() {

    companion object{
        const val pinCode = "94040"
        const val countryCode = "us"
        const val language = "en"
        const val units = "imperial"
    }

    //Storing data coming from API
    var temp:Int? = 4
    var main:String? = "Clear"
    var visibility:String? = "16093"
    var humidity:String? = "82"

    //Storing data coming from Intent
//    val pin = intent.getStringExtra(pinCode)
//    val country = intent.getStringExtra(countryCode)
//    val lang = intent.getStringExtra(language)
//    val uni = intent.getStringExtra(units)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_data)

        loadWeather()
//        temperature.text = temp
//        cloudText.text = main
//        visibiltytext.text = visibility
//        humidityText.text = humidity
    }


    private fun loadWeather(){

        val pin = intent.getStringExtra(pinCode)
        val country = intent.getStringExtra(countryCode)
        val lang = intent.getStringExtra(language)
        val uni = intent.getStringExtra(units)


        val queue = Volley.newRequestQueue(this)
        val url = "http://api.openweathermap.org/data/2.5/weather?zip=$pin,$country&appid=a9e00f2469ab16ff25796adb23911c2c&lang=$lang&units=$uni"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->

//                temp = response.getString("name[0]")
//                main = response.getString("weather[1]")
//                visibility = response.getString("visibility")
//                humidity = response.getString("main[5]")

                var mainObject:org.json.JSONObject = response.getJSONObject("main")
                var array:JSONArray = response.getJSONArray("weather")
                var obj:org.json.JSONObject = array.getJSONObject(0)

                temp = (mainObject.getDouble("temp").toInt())
                println("temp is $temp")
                temperature.text="$temp\u2103"

                println("Pin Code = $pin")

                humidity = (mainObject.getInt("humidity").toString())
                println("humidity is $humidity")
                humidityText.text = humidity

                main = obj.getString("main")
                println("description: $main")
                cloudText.text = main

                visibility = response.getString("visibility")
                visibiltytext.text = visibility

            },
            {
//                Toast.makeText(this,"Error", Toast.LENGTH_LONG).show()
            })
        queue.add(jsonObjectRequest)
    }

    fun reloadWeather(view: android.view.View) {
        loadWeather()
    }
}