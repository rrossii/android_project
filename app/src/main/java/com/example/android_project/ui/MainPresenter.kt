package com.example.android_project.ui

import android.content.Context
import android.util.Log
import com.example.android_project.DiHelper
import com.example.android_project.api.IDataSource
import com.example.android_project.data.entities.WifiNetwork
import com.example.android_project.data.server_responses.get_all_networks.GetAllNetworksResponse

class MainPresenter(val view: MainContract.View, val applicationContext: Context) : MainContract.Presenter {
    private val repository: MainContract.Repository = DiHelper.getRepository(applicationContext)
    override fun loadWifiNetworkData() {
        Log.d("API", "load wifi networks data")

        repository.getAllWifiNetworksFromServer(object: IDataSource.WifiNetworkCallback { // тут оверайдимо поведінку колбек функції під кожен запит
            override fun onSuccess(networksResponse: GetAllNetworksResponse) {
                networksResponse.record?.let { record ->
                    record.networks.let { networks ->
                        repository.saveNetworkDataFromServerToLocalDatabase(networks)
                        view.logReceivedNetworksFromServer(networks)
                    }
                }
            }

            override fun onFailure() {
                view.logLoadingNetworksError()
            }
        })
    }

    override fun clickOnAddNetworkBtn() {
        view.navigateToAddNetworkScreen()
    }

    override fun getAllNetworksData() {
        val wifiNetworks: List<WifiNetwork>? = repository.getAllNetworksFromDb()
        view.showNetworksDataInRecyclerView(wifiNetworks)
    }

}