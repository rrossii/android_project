package com.example.android_project.ui

import com.example.android_project.data.entities.WifiNetwork

interface AddNetworkContract {
    interface View {
        fun navigateToMainScreen()
    }
    interface Presenter {
        fun clickOnCancelBtn()
        fun clickOnSaveNetworkBtn(networkToAdd: WifiNetwork)
    }
}