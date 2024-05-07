package com.example.android_project.api

import com.example.android_project.api.callbacks.WifiNetworkCallback
import com.example.android_project.data.server_responses.get_all_networks.GetAllNetworksResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiService {
    var api: Api
    val X_MASTER_KEY: String = "\$2a\$10\$pGc3gmiYhFM7FrJ3uuCATOUpCozl8nK4n0qtmzchZOHTsdohjLrYO"

    init {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.jsonbin.io/v3/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(Api::class.java)
    }

    fun getAllWifiNetworks(callback: WifiNetworkCallback) {
        api.getAllWifiNetworks(X_MASTER_KEY).enqueue(object: Callback<GetAllNetworksResponse> {
            override fun onResponse(call: Call<GetAllNetworksResponse>,
                                    response: Response<GetAllNetworksResponse>) {
                if (response.code() == 200 && response.body() != null) {
                    callback.onSuccess(response.body()!!)
                } else {
                    callback.onFailure()
                }
            }

            override fun onFailure(call: Call<GetAllNetworksResponse>, t: Throwable) {
                callback.onFailure()
            }
        })
    }
}