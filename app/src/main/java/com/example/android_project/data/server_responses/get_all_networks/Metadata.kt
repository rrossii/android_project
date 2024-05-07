package com.example.android_project.data.server_responses.get_all_networks

import com.google.gson.annotations.SerializedName

data class Metadata (

    @SerializedName("id"        ) var id        : String?  = null,
    @SerializedName("private"   ) var private   : Boolean? = null,
    @SerializedName("createdAt" ) var createdAt : String?  = null

)
