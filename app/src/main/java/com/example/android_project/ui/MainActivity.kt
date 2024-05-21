package com.example.android_project.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.android_project.DiHelper
import com.example.android_project.R
import com.example.android_project.data.WifiNetworkDatabase
import com.example.android_project.data.entities.WifiNetwork
import com.example.android_project.data.server_responses.get_all_networks.WifiNetworkFromJSON

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var testDb: WifiNetworkDatabase

    private lateinit var presenter: MainContract.Presenter
    private lateinit var addNetworkBtn: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: WifiNetworkAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_of_wifi_networks)

        // init
        testDB()
        initUI()
        presenter = DiHelper.getMainPresenter(this, this)

        presenter.loadWifiNetworkData() // from server
        presenter.getAllNetworksData()

        addNetworkBtn.setOnClickListener {
            presenter.clickOnAddNetworkBtn()
        }
    }

    private fun initUI() {
        addNetworkBtn = findViewById(R.id.addNewNetworkBtn)

        recyclerView = findViewById(R.id.listOfNetworks)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = WifiNetworkAdapter(this, emptyList())
        recyclerView.adapter = adapter
    }

    override fun navigateToAddNetworkScreen() {
        try {
            val intent = Intent(this, AddNetworkActivity::class.java)
            startActivity(intent)
        } catch (e: Exception) {
            Log.e("MainActivity", "Error starting AddNetworkActivity: ${e.message}")
            e.printStackTrace()
        }
    }

    override fun showNetworksDataInRecyclerView(wifiNetworks: List<WifiNetwork>?) {
        adapter.updateData(wifiNetworks)
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

    override fun logLoadingNetworksError() {
        Log.d("API", "Error while loading networks data")
    }

    override fun logReceivedNetworksFromServer(networks: ArrayList<WifiNetworkFromJSON>) {
        for (network in networks) {
            val ssid = network.ssid
            if (ssid != null) {
                Log.d("API", "Got $ssid")
            } else {
                Log.d("API", "Network's ssid is null")
            }
        }
    }
}
