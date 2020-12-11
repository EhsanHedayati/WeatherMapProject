package com.mylab.weathermapproject.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.mylab.weathermapproject.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket


class SplashFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        view.findViewById<Button>(R.id.start).setOnClickListener {

            CoroutineScope(Dispatchers.IO).launch {
                val available = isInternetAvailable(requireContext())
                val reachable = isHostReachable(it)
                if (available && reachable) {
                    findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToMapsFragment())
                } else {
                    view.let { view ->
                        Snackbar.make(
                            view,
                            "No internet available OR\n" + "Remote host has a problem",
                            Snackbar.LENGTH_INDEFINITE
                        )
                            .setAction("got it") {
                                //ToDo
                            }
                            .show()
                    }

                }
            }


        }

    }

    private fun isInternetAvailable(context: Context): Boolean {
        var isInternetAccessible = false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            isInternetAccessible = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    isInternetAccessible = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }

                }
            }
        }

        return isInternetAccessible
    }

    private suspend fun isHostReachable(view: View): Boolean {
        var exist = false

        try {
            val socketAddress = InetSocketAddress("api.openweathermap.org", 80)
            val sock = Socket()
            val timeOutMS = 2000
            sock.connect(socketAddress, timeOutMS)

            exist = true

        } catch (e: IOException) {
            withContext(Dispatchers.Main) {
                view.let { view ->
                    Snackbar.make(
                        view,
                        "There is a connection problem with remote host.",
                        Snackbar.LENGTH_INDEFINITE
                    )
                        .setAction("got it") {
                            //ToDo
                        }
                        .show()
                }
            }

        }
        return exist
    }
}