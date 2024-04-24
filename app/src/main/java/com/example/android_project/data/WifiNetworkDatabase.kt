package com.example.android_project.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.android_project.data.entities.WifiNetwork
import com.example.android_project.data.daos.WifiNetworkDao

@Database(
    entities = [WifiNetwork::class],
    version = 1
)
abstract class WifiNetworkDatabase: RoomDatabase() {
    abstract fun dao(): WifiNetworkDao // or val dao
}