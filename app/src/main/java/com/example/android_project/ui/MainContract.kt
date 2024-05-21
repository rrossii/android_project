package com.example.android_project.ui

import com.example.android_project.data.entities.WifiNetwork
import com.example.android_project.data.server_responses.get_all_networks.WifiNetworkFromJSON

interface MainContract {
    interface IView {
        fun clickOnBtnAddNetwork()
        fun displayNetworks(networks: ArrayList<WifiNetworkFromJSON>)
        fun displayLoadingNetworksError()

    }
    interface IPresenter {
        fun loadWifiNetworkData()
        fun saveNetworkDataFromServerToLocalDatabase(networks: ArrayList<WifiNetworkFromJSON>)
        fun getAllNetworks(): List<WifiNetwork>?
        fun getNetwork(ssid: String): WifiNetwork?
        fun insertNetwork(network: WifiNetwork)
        fun deleteNetwork(network: WifiNetwork)
    }
}