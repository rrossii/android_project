package com.example.android_project.api

import com.example.android_project.data.server_responses.get_all_networks.GetAllNetworksResponse

interface IDataSource {
    fun getAllWifiNetworks(callback: WifiNetworkCallback)

    interface WifiNetworkCallback {
        fun onSuccess(networksResponse: GetAllNetworksResponse)
        fun onFailure()
    }
}