package com.example.android_project

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class AddNetworkActivity : AppCompatActivity() {
    private lateinit var cancelBtn: Button
    private lateinit var saveBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_new_network)

        initUI()

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
            // insert network to db
        }
    }
    private fun initUI() {
        cancelBtn = findViewById(R.id.newNetworkCancelButton)
        saveBtn = findViewById(R.id.newNetworkSaveButton)
    }
}