package com.example.android_project.ui

import com.example.android_project.api.IDataSource
import com.example.android_project.data.entities.WifiNetwork
import com.example.android_project.data.server_responses.get_all_networks.WifiNetworkFromJSON

interface MainContract {
    interface View {
        fun showNetworksDataInRecyclerView(wifiNetworks: List<WifiNetwork>?)
        fun logReceivedNetworksFromServer(networks: ArrayList<WifiNetworkFromJSON>)
        fun logLoadingNetworksError()
        fun navigateToAddNetworkScreen()
    }
    interface Presenter {
        fun loadWifiNetworkData()
        fun clickOnAddNetworkBtn()
        fun getAllNetworksData()
    }
    interface Repository {
        fun getAllWifiNetworksFromServer(callback: IDataSource.WifiNetworkCallback)
        fun getAllNetworksFromDb(): List<WifiNetwork>?
        fun getNetworkFromDb(ssid: String): WifiNetwork?
        fun insertNetworkToDb(network: WifiNetwork)
        fun deleteNetworkFromDb(network: WifiNetwork)
        fun saveNetworkDataFromServerToLocalDatabase(networks: ArrayList<WifiNetworkFromJSON>)
    }
}