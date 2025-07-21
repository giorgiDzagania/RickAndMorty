package com.example.rickandmorty.data.internet_connection

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkCapabilities
import androidx.core.content.getSystemService
import com.example.rickandmorty.domain.internet_connection.ConnectivityObserver
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class AndroidConnectivityObserver(
    private val context: Context
) : ConnectivityObserver {

    private val connectivityManager =
        context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

    override val isConnected: Flow<Boolean>
        get() = callbackFlow {

            val activeNetwork = connectivityManager.activeNetwork
            val currentCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
            val isCurrentlyConnected = currentCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED) == true
            trySend(isCurrentlyConnected)

            val callBack = object : NetworkCallback() {
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    trySend(true)
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    trySend(false)
                }

                override fun onUnavailable() {
                    super.onUnavailable()
                    trySend(false)
                }

                override fun onCapabilitiesChanged(
                    network: Network,
                    networkCapabilities: NetworkCapabilities
                ) {
                    super.onCapabilitiesChanged(network, networkCapabilities)
                    val connected = networkCapabilities.hasCapability(
                        NetworkCapabilities.NET_CAPABILITY_VALIDATED
                    )
                    trySend(connected)
                }
            }

            connectivityManager.registerDefaultNetworkCallback(callBack)
            awaitClose {
                connectivityManager.unregisterNetworkCallback(callBack)
            }
        }

}
