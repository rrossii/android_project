package com.example.android_project.api

import com.example.android_project.data.server_responses.get_all_networks.GetAllNetworksResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface Api {
    @GET("b/6639ee06ad19ca34f865b676")
    fun getAllWifiNetworks(@Header("X-MASTER-KEY") secretKey: String): Call<GetAllNetworksResponse>
}