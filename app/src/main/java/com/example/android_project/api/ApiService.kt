package com.example.android_project.api

import com.example.android_project.api.callbacks.WifiNetworkCallback
import com.example.android_project.data.server_responses.get_all_networks.GetAllNetworksResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiService {
    var api: Api
    val X_MASTER_KEY: String = "\$2a\$10\$pGc3gmiYhFM7FrJ3uuCATOUpCozl8nK4n0qtmzchZOHTsdohjLrYO"
    val retrofitHelper: RetrofitApiHelper = RetrofitApiHelper()

    init {
        retrofitHelper.init()
        api = retrofitHelper.getRetrofitInstance().create(Api::class.java)
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