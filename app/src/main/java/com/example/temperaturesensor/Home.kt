package com.example.temperaturesensor

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONTokener

class Home : Fragment() {


    @SuppressLint("SetTextI18n")


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_home, container, false)
        val maxtemp = 25
        val queue = Volley.newRequestQueue(view.context)
        val url = "https://io.adafruit.com/api/v2/alex9301/feeds/test-data/data" //URL
        var urlStuff = ""

        val stringRequest = StringRequest(Request.Method.GET, url,
            { response ->
                // Display the first 500 characters of the response string.
                urlStuff = response
                val jsonArray = JSONTokener(urlStuff).nextValue() as JSONArray
                val id = jsonArray.getJSONObject(0).getString("value") // Get data

                //Updating info on app
                val textView : TextView = view.findViewById(R.id.temp)
                textView.text = "$id°C"
                if (id > maxtemp.toString())
                {
                    val indicatorwarm : TextView = view.findViewById(R.id.indicator)
                    indicatorwarm.text = "Too Warm"
                }
                else if (id < maxtemp.toString())
                {
                    val indicatorcold : TextView = view.findViewById(R.id.indicator)
                    indicatorcold.text = "Too Cold"
                }

            },
            { Log.i("b", "That didn't work!") })
        queue.add(stringRequest)

        return view

    }

    fun powerButton (inflater: LayoutInflater, container: ViewGroup?,
                     savedInstanceState: Bundle?)
    {
        val view =  inflater.inflate(R.layout.fragment_home, container, false)

        val btn : ImageButton = view.findViewById(R.id.power)
        btn.setOnClickListener()
        {
            print("hello")
        }
    }

}