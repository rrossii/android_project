package com.example.android_project.ui

import androidx.lifecycle.ViewModel
import com.example.android_project.data.entities.WifiNetwork
import com.example.android_project.data.repositories.WifiNetworkRepository

class WifiNetworkViewModel(private val repository: WifiNetworkRepository) : ViewModel() {
    fun getAllNetworks(): List<WifiNetwork>? = repository.getAllNetworks()

    fun getNetwork(ssid: String): WifiNetwork? = repository.getNetwork(ssid)

    fun insertNetwork(network: WifiNetwork) = repository.insertNetwork(network)

    fun deleteNetwork(network: WifiNetwork) = repository.deleteNetwork(network)
}