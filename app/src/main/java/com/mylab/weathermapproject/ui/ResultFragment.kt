package com.mylab.weathermapproject.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mylab.weathermapproject.databinding.FragmentResultBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class ResultFragment : Fragment() {

    private var temp : Double? = null

    private val sharedModel: SharedViewModel by sharedViewModel()
    private val resultViewModel : ResultViewModel by inject<ResultViewModel>()
    lateinit var binding: FragmentResultBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentResultBinding.inflate(inflater)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        sharedModel.sharedResponseWeather.observe(viewLifecycleOwner) { it ->

            binding.weatherResponse = it.data
            temp = it.data?.main?.temp
            binding.convertedTemp = temp?.let { temp -> resultViewModel.convertedTemp(temp) }

        }






    }



}

