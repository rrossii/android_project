package com.example.android_project.data.server_responses.get_all_networks

import com.google.gson.annotations.SerializedName

data class Networks (
    @SerializedName("networks" ) var networks : ArrayList<WifiNetworkFromJSON> = arrayListOf()
)
