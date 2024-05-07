package com.example.android_project.api.callbacks

import com.example.android_project.data.server_responses.get_all_networks.GetAllNetworksResponse

interface WifiNetworkCallback {
    fun onSuccess(networksResponse: GetAllNetworksResponse)
    fun onFailure()
}