package com.example.android_project.data.server_responses.get_all_networks

import com.google.gson.annotations.SerializedName

data class GetAllNetworksResponse(
    @SerializedName("record"   ) var record   : Networks?   = Networks(),
    @SerializedName("metadata" ) var metadata : Metadata? = Metadata()
)