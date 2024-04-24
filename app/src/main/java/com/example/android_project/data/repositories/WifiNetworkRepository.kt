package com.example.android_project.data.repositories

import com.example.android_project.data.WifiNetworkDatabase
import com.example.android_project.data.entities.WifiNetwork

class WifiNetworkRepository(private val db: WifiNetworkDatabase) {
    fun getAllNetworks(): List<WifiNetwork>? = db.dao().getAllNetworks()

    fun getNetwork(ssid: String): WifiNetwork? = db.dao().getNetwork(ssid)

    fun insertNetwork(network: WifiNetwork) = db.dao().insertNetwork(network)

    fun deleteNetwork(network: WifiNetwork) = db.dao().deleteNetwork(network)
}