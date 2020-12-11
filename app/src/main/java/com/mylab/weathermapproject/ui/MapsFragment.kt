package com.mylab.weathermapproject.ui

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.snackbar.Snackbar
import com.mylab.weathermapproject.R
import com.mylab.weathermapproject.errorhandling.Status
import kotlinx.android.synthetic.main.fragment_maps.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MapsFragment : Fragment() {
    private val sharedModel: SharedViewModel by sharedViewModel()


    private val callback = OnMapReadyCallback { googleMap ->
        val latLng1 = LatLng(34.36, 52.33)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng1, 6f))

        sharedModel.sharedResponseWeather.observe(viewLifecycleOwner) {

            when (it.status) {
                Status.LOADING -> {
                    //ToDo
                }
                Status.ERROR -> {

                    view?.let { view ->
                        Snackbar.make(
                            view,
                            it.message.toString(),
                            Snackbar.LENGTH_SHORT
                        )
                            .setAction("got it") {
                                //ToDo
                            }
                            .show()
                    }

                }
                Status.SUCCESS -> {
                    val latLng2 = LatLng(it.data?.coord?.lat!!, it.data.coord.lon!!)
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng2, 6f))
                }
            }


        }


        googleMap.setOnCameraIdleListener {
            val center = googleMap.cameraPosition.target
            val lat = center.latitude
            val lng = center.longitude

            query_button.setOnClickListener {

                sharedModel.fetchData(lat, lng)
                findNavController().navigate(R.id.action_mapsFragment_to_resultFragment)
            }
            sharedModel.errorMessage.observe(viewLifecycleOwner) {

                view?.let { view ->
                    Snackbar.make(
                        view,
                        it,
                        Snackbar.LENGTH_SHORT
                    )
                        .setAction("got it") {
                            //ToDo
                        }
                        .show()
                }

            }

        }


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)


    }


}