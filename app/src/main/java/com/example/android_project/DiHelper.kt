package com.example.android_project

import android.content.Context
import androidx.room.Room
import com.example.android_project.api.ApiService
import com.example.android_project.api.IDataSource
import com.example.android_project.api.RetrofitApiHelper
import com.example.android_project.data.MainRepository
import com.example.android_project.data.WifiNetworkDatabase
import com.example.android_project.ui.AddNetworkContract
import com.example.android_project.ui.AddNetworkPresenter
import com.example.android_project.ui.MainContract
import com.example.android_project.ui.MainPresenter

class DiHelper {
    companion object {
        private var mainPresenter: MainContract.Presenter? = null
        private var addNetworkPresenter: AddNetworkContract.Presenter? = null
        private var mainRepository: MainContract.Repository? = null
        private var retrofitHelper: RetrofitApiHelper? = null
        private var apiService: IDataSource? = null

        fun getMainPresenter(view: MainContract.View, applicationContext: Context): MainContract.Presenter {
            if (mainPresenter == null) {
                mainPresenter = MainPresenter(view, applicationContext)
            }
            return mainPresenter ?: throw IllegalStateException("mainPresenter instance is null")
        }
        fun getAddNetworkPresenter(view: AddNetworkContract.View, applicationContext: Context): AddNetworkContract.Presenter {
            if (addNetworkPresenter == null) {
                addNetworkPresenter = AddNetworkPresenter(view, applicationContext)
            }
            return addNetworkPresenter ?: throw IllegalStateException("addNetworkPresenter instance is null")
        }

        fun getRepository(applicationContext: Context): MainContract.Repository {
            if (mainRepository == null) {
                // за рахунок патерна синглтон ми створюємо базу даних лише раз
                val db: WifiNetworkDatabase = Room.databaseBuilder(
                    applicationContext,
                    WifiNetworkDatabase::class.java,
                    "wifi_network_database"
                ).allowMainThreadQueries().build()

                mainRepository = MainRepository(db)
            }
            return mainRepository ?: throw IllegalStateException("mainRepository instance is null")
        }

        fun getRetrofitHelper(): RetrofitApiHelper {
            if (retrofitHelper == null) {
                retrofitHelper = RetrofitApiHelper()
                retrofitHelper?.init()
            }
            return retrofitHelper ?: throw IllegalStateException("retrofitHelper instance is null")
        }

        fun getApiService(): IDataSource {
            if (apiService == null) {
                apiService = ApiService()
            }
            return apiService ?: throw IllegalStateException("apiService instance is null")
        }
    }
}