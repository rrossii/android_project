package com.example.android_project.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.android_project.R
import com.example.android_project.data.entities.WifiNetwork
import com.example.android_project.data.repositories.WifiNetworkRepository

class AddNetworkActivity : AppCompatActivity() {
    private lateinit var cancelBtn: Button
    private lateinit var saveBtn: Button
    private lateinit var ssidEditText: EditText
    private lateinit var passwordEditText: EditText

    private lateinit var viewModel: WifiNetworkViewModel
    private lateinit var factory: WifiNetworkViewModelFactory
    private lateinit var repository: WifiNetworkRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_new_network)

        initUI()
        initViewModel()

        cancelBtn.setOnClickListener {
            try {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } catch (e: Exception) {
                Log.e("MainActivity", "Error starting MainActivity: ${e.message}")
                e.printStackTrace()
            }
        }
        saveBtn.setOnClickListener {
            val ssid = ssidEditText.text.toString()
            val password = passwordEditText.text.toString()
            val networkToAdd = WifiNetwork(ssid, password, false)
            viewModel.insertNetwork(networkToAdd)
            val n = viewModel.getAllNetworks()

//            if (ssid.isEmpty()) {
//                Toast.makeText(applicationContext, "Please enter a ssid", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }


            try {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } catch (e: Exception) {
                Log.e("MainActivity", "Error starting MainActivity: ${e.message}")
                e.printStackTrace()
            }
        }
    }
    private fun initUI() {
        cancelBtn = findViewById(R.id.newNetworkCancelButton)
        saveBtn = findViewById(R.id.newNetworkSaveButton)
        ssidEditText = findViewById(R.id.editTextNewNetworkSsid)
        passwordEditText = findViewById(R.id.editTextNewNetworkPassword)
    }

    private fun initViewModel() {
        val db = MainActivity.db
        repository = WifiNetworkRepository(db)
        factory = WifiNetworkViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(WifiNetworkViewModel::class.java)
    }
}