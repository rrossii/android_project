package com.example.android_project.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android_project.data.repositories.WifiNetworkRepository

@Suppress("UNCHECKED_CAST")
class WifiNetworkViewModelFactory(private val repository: WifiNetworkRepository)
    : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WifiNetworkViewModel(repository) as T
    }
}