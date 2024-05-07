package com.example.android_project.data.server_responses.get_all_networks

import com.google.gson.annotations.SerializedName

data class WifiNetworkFromJSON (
    @SerializedName("ssid"      ) var ssid      : String?  = null,
    @SerializedName("password"  ) var password  : String?  = null,
    @SerializedName("connected" ) var connected : Boolean? = null
)