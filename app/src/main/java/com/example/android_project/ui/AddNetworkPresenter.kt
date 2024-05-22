package com.example.android_project.ui

import android.content.Context
import com.example.android_project.DiHelper
import com.example.android_project.data.entities.WifiNetwork

class AddNetworkPresenter(val view: AddNetworkContract.View, val applicationContext: Context)
    : AddNetworkContract.Presenter {

    private val repository: MainContract.Repository = DiHelper.getRepository(applicationContext)

    override fun clickOnSaveNetworkBtn(networkToAdd: WifiNetwork) {
        repository.insertNetworkToDb(networkToAdd)
    }

}