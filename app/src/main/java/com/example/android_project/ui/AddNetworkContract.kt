package com.example.android_project.ui

import com.example.android_project.data.entities.WifiNetwork

interface AddNetworkContract {
    interface View {

    }
    interface Presenter {
        fun clickOnSaveNetworkBtn(networkToAdd: WifiNetwork)
    }
}