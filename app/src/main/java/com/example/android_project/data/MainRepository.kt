package com.example.android_project.data

import com.example.android_project.DiHelper
import com.example.android_project.api.IDataSource
import com.example.android_project.data.entities.WifiNetwork
import com.example.android_project.data.server_responses.get_all_networks.WifiNetworkFromJSON
import com.example.android_project.ui.MainContract

class MainRepository(private val db: WifiNetworkDatabase) : MainContract.Repository {
    private val apiService: IDataSource = DiHelper.getApiService()

    override fun getAllWifiNetworksFromServer(callback: IDataSource.WifiNetworkCallback) {
        apiService.getAllWifiNetworks(callback)
    }
    override fun getAllNetworksFromDb(): List<WifiNetwork>? = db.dao().getAllNetworks()

    override fun getNetworkFromDb(ssid: String): WifiNetwork? = db.dao().getNetwork(ssid)

    override fun insertNetworkToDb(network: WifiNetwork) = db.dao().insertNetwork(network)

    override fun deleteNetworkFromDb(network: WifiNetwork) = db.dao().deleteNetwork(network)

    override fun saveNetworkDataFromServerToLocalDatabase(networks: ArrayList<WifiNetworkFromJSON>) {
        for (network in networks) {
            val ssid = network.ssid
            val password = network.password
            val connected = network.connected
            val networkToAdd = WifiNetwork(ssid!!, password!!, connected!!)
            insertNetworkToDb(networkToAdd)
        }
    }
}