package com.example.android_project.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.android_project.data.entities.WifiNetwork

@Dao
interface WifiNetworkDao {
    @Query("SELECT * FROM WifiNetwork")
    fun getAllNetworks() : List<WifiNetwork>? // додати флов

    @Query("SELECT * FROM WifiNetwork WHERE ssid=:ssid")
    fun getNetwork(ssid: String): WifiNetwork?

    @Insert(onConflict = OnConflictStrategy.REPLACE) // or output error
    fun insertNetwork(network: WifiNetwork)

    @Query("UPDATE WifiNetwork SET connected=:connected")
    fun updateNetworkConnectionStatus(connected: Boolean)

    @Delete
    fun deleteNetwork(network: WifiNetwork)
}