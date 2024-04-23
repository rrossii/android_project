package com.example.android_project

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [WifiNetwork::class],
    version = 1
)
abstract class WifiNetworkDatabase: RoomDatabase() {
    abstract fun dao(): WifiNetworkDao // or val dao
}