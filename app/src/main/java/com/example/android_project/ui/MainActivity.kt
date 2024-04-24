package com.example.android_project.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.android_project.R
import com.example.android_project.data.daos.WifiNetworkDao
import com.example.android_project.data.WifiNetworkDatabase
import com.example.android_project.data.entities.WifiNetwork

class MainActivity : AppCompatActivity() {
    private lateinit var db: WifiNetworkDatabase
    private lateinit var dao: WifiNetworkDao

    private lateinit var addNetworkBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_of_wifi_networks)

        initUI()
        createDB()
        testDB()

        val recyclerView: RecyclerView = findViewById(R.id.listOfNetworks)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val w1 = WifiNetwork("Network 1", "pass1", false)
        val w2 = WifiNetwork("Network 2", "pass1", false)
        val w3 = WifiNetwork("Network 3", "pass1", false)
        val w4 = WifiNetwork("Network 4", "pass1", false)
        val w5 = WifiNetwork("Network 5", "pass1", false)
        val w6 = WifiNetwork("Network 6", "pass1", false)

        val wifiNetworks = listOf(
            w1, w2, w3, w4, w5, w6
        )

        val adapter = WifiNetworkAdapter(this, wifiNetworks)
        recyclerView.adapter = adapter

        addNetworkBtn.setOnClickListener {
            addNewNetwork()
        }

    }
    private fun addNewNetwork() {
        try {
            val intent = Intent(this, AddNetworkActivity::class.java)
            startActivity(intent)
        } catch (e: Exception) {
            Log.e("MainActivity", "Error starting AddNetworkActivity: ${e.message}")
            e.printStackTrace()
        }
    }

    private fun initUI() {
        addNetworkBtn = findViewById(R.id.addNewNetworkBtn)
    }

    private fun createDB() {
        db = Room.databaseBuilder(
            applicationContext,
            WifiNetworkDatabase::class.java,
            "wifi_network_database"
        ).allowMainThreadQueries().build()

        dao = db.dao()

        Log.d("TEST", "Database created")
    }

    private fun testDB() {
        db.clearAllTables()

        testInsert()
        testReadAll()
        testRead()
        testDelete()
        testReadAll()

        db.clearAllTables()
    }

    private fun testReadAll() {
        val networks = dao.getAllNetworks()

        if (networks == null) {
            Log.e("TEST", "No networks in database")
        } else {
            for (network in networks) {
                Log.d("TEST", "Got $network")
            }
        }

    }

    private fun testRead() {
        val net1 = WifiNetwork("net1", "1234", false)
        val ssid = net1.ssid
        val retrievedNetwork = dao.getNetwork(ssid)

        if (retrievedNetwork == null) {
            Log.e("TEST", "Network ${net1.ssid} not found in database")
        } else {
            Log.d("TEST", "Got ${retrievedNetwork.ssid}")
        }
    }

    private fun testInsert() {
        val net1 = WifiNetwork("net1", "1234", false)
        val net2 = WifiNetwork("net2", "1234", false)
        dao.insertNetwork(net1)
        dao.insertNetwork(net2)

        val retrieved = dao.getNetwork(net1.ssid)

        if (retrieved != null && retrieved.ssid == net1.ssid && retrieved.password == net1.password && retrieved.connected == net1.connected) {
            Log.d("TEST", "Insert ${net1.ssid} successfully")
        } else {
            Log.d("TEST", "Error while inserting")
        }
    }

    private fun testDelete() {
        val net1 = WifiNetwork("net1", "1234", false)
        dao.deleteNetwork(net1)

        val retrieved = dao.getNetwork(net1.ssid)
        if (retrieved == null) {
            Log.d("TEST", "Deleted ${net1.ssid}")
        } else {
            Log.d("TEST", "Cannot delete ${net1.ssid}")
        }

    }
}
