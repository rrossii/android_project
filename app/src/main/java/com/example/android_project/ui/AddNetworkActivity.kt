package com.example.android_project.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.android_project.DiHelper
import com.example.android_project.R
import com.example.android_project.data.entities.WifiNetwork

class AddNetworkActivity : AppCompatActivity(), AddNetworkContract.View {
    private lateinit var cancelBtn: Button
    private lateinit var saveBtn: Button
    private lateinit var ssidEditText: EditText
    private lateinit var passwordEditText: EditText

    private lateinit var presenter: AddNetworkContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_new_network)

        initUI()
        presenter = DiHelper.getAddNetworkPresenter(this, this)
//        presenter = DiHelper.getAddNetworkPresenter(this, applicationContext)

        cancelBtn.setOnClickListener {
            presenter.clickOnCancelBtn()
        }
        saveBtn.setOnClickListener {
            val ssid = ssidEditText.text.toString()
            val password = passwordEditText.text.toString()
            val networkToAdd = WifiNetwork(ssid, password, false)
            presenter.clickOnSaveNetworkBtn(networkToAdd)

//            if (ssid.isEmpty()) {
//                Toast.makeText(applicationContext, "Please enter a ssid", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }
        }
    }
    private fun initUI() {
        cancelBtn = findViewById(R.id.newNetworkCancelButton)
        saveBtn = findViewById(R.id.newNetworkSaveButton)
        ssidEditText = findViewById(R.id.editTextNewNetworkSsid)
        passwordEditText = findViewById(R.id.editTextNewNetworkPassword)
    }

    override fun navigateToMainScreen() {
        try {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } catch (e: Exception) {
            Log.e("AddNetworkActivity", "Error starting MainActivity: ${e.message}")
            e.printStackTrace()
        }
    }
}