package com.example.android_project

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WifiNetwork(
    @PrimaryKey(autoGenerate = false)
    val ssid: String,
    @ColumnInfo
    val password: String, // make hash and logic
    @ColumnInfo
    val connected: Boolean,
)