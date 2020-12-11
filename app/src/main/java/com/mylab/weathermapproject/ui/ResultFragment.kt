package com.mylab.weathermapproject.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mylab.weathermapproject.R
import kotlinx.android.synthetic.main.fragment_result.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class ResultFragment : Fragment() {
    private val sharedModel: SharedViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        return inflater.inflate(R.layout.fragment_result, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        sharedModel.sharedResponseWeather.observe(viewLifecycleOwner) { it ->

            it.let {
                val convertedTemp = it.data?.main?.temp?.minus(273.15)?.toInt()
                text_city_name.text = it.data?.name
                text_value_main.text = it.data?.weather?.get(0)?.main
                text_value_temperature.text = getString(R.string.centigrade_unit, convertedTemp)
                text_value_pressure.text = it.data?.main?.pressure.toString()
                text_value_humidity.text = it.data?.main?.humidity.toString()
                text_value_wind_speed.text = it.data?.wind?.speed.toString()
            }
        }


    }


}

