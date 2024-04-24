package com.example.android_project.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.android_project.R
import com.example.android_project.data.daos.WifiNetworkDao
import com.example.android_project.data.WifiNetworkDatabase
import com.example.android_project.data.entities.WifiNetwork
import com.example.android_project.data.repositories.WifiNetworkRepository

class MainActivity : AppCompatActivity() {
    companion object {
        public lateinit var db: WifiNetworkDatabase
            private set
    }
    private lateinit var testDb: WifiNetworkDatabase
    private lateinit var dao: WifiNetworkDao
    private lateinit var viewModel: WifiNetworkViewModel
    private lateinit var factory: WifiNetworkViewModelFactory
    private lateinit var repository: WifiNetworkRepository

    private lateinit var addNetworkBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_of_wifi_networks)

        createDB()
        testDB()
        initUI()
        initViewModel()

        addNetworkBtn.setOnClickListener {
            addNewNetwork()
        }

        val wifiNetworks = viewModel.getAllNetworks()

        val recyclerView: RecyclerView = findViewById(R.id.listOfNetworks)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = wifiNetworks?.let { WifiNetworkAdapter(this, it) }
        recyclerView.adapter = adapter
    }
    private fun initUI() {
        addNetworkBtn = findViewById(R.id.addNewNetworkBtn)
    }

    private fun initViewModel() {
        repository = WifiNetworkRepository(db)
        factory = WifiNetworkViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(WifiNetworkViewModel::class.java)
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
        testDb = Room.databaseBuilder(
            applicationContext,
            WifiNetworkDatabase::class.java,
            "wifi_network_test_database"
        ).allowMainThreadQueries().build()

        testInsert()
        testGetAll()
        testGet()
        testDelete()
        testGetAll()

        testDb.clearAllTables()
    }

    private fun testGetAll() {
        val testDao = testDb.dao()
        val networks = testDao.getAllNetworks()

        if (networks == null) {
            Log.e("TEST", "No networks in database")
        } else {
            for (network in networks) {
                Log.d("TEST", "Got $network")
            }
        }

    }

    private fun testGet() {
        val testDao = testDb.dao()
        val net1 = WifiNetwork("net1", "1234", false)
        val ssid = net1.ssid
        val retrievedNetwork = testDao.getNetwork(ssid)

        if (retrievedNetwork == null) {
            Log.e("TEST", "Network ${net1.ssid} not found in database")
        } else {
            Log.d("TEST", "Got ${retrievedNetwork.ssid}")
        }
    }

    private fun testInsert() {
        val testDao = testDb.dao()
        val net1 = WifiNetwork("net1", "1234", false)
        val net2 = WifiNetwork("net2", "1234", false)
        testDao.insertNetwork(net1)
        testDao.insertNetwork(net2)

        val retrieved = testDao.getNetwork(net1.ssid)

        if (retrieved != null && retrieved.ssid == net1.ssid && retrieved.password == net1.password && retrieved.connected == net1.connected) {
            Log.d("TEST", "Insert ${net1.ssid} successfully")
        } else {
            Log.d("TEST", "Error while inserting")
        }
    }

    private fun testDelete() {
        val testDao = testDb.dao()
        val net1 = WifiNetwork("net1", "1234", false)
        testDao.deleteNetwork(net1)

        val retrieved = testDao.getNetwork(net1.ssid)
        if (retrieved == null) {
            Log.d("TEST", "Deleted ${net1.ssid}")
        } else {
            Log.d("TEST", "Cannot delete ${net1.ssid}")
        }
    }
}
