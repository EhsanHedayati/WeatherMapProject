package com.mylab.weathermapproject.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.mylab.weathermapproject.R
import org.koin.android.ext.android.inject


class SplashFragment : Fragment() {


    private val splashViewModel: SplashViewModel by inject<SplashViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!splashViewModel.isStatusOk()) {
            view.let { view ->
                Snackbar.make(
                    view,
                    "No available internet connection\n" +
                            "check internet connection status",
                    Snackbar.LENGTH_INDEFINITE
                )
                    .setAction("got it") {
                        //ToDo
                    }
                    .show()

            }


        }




        view.findViewById<Button>(R.id.start).setOnClickListener {


            findNavController().navigate(R.id.action_splashFragment_to_mapsFragment)


        }

    }


}








