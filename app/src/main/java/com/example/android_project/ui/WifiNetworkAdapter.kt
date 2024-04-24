package com.example.android_project.ui

import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android_project.R
import com.example.android_project.data.entities.WifiNetwork

class WifiNetworkAdapter(private val context: Context, private val wifiNetworks: List<WifiNetwork>) : RecyclerView.Adapter<WifiNetworkAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val networkNameSSID: TextView = itemView.findViewById(R.id.networkSSID)

        fun bindData(network: WifiNetwork) {
            networkNameSSID.text = network.ssid
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.wifi_network_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return wifiNetworks.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val wifiNetwork = wifiNetworks[position]
        holder.bindData(wifiNetwork)

        holder.itemView.setOnClickListener {
            showDialog(position)
        }
    }

    private fun showDialog(networkPosition: Int) {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.dialog_connect_to_network)
        dialog.setCancelable(true)
        val dialogCancelBtn: Button = dialog.findViewById(R.id.cancelButton)
        val dialogConnectBtn: Button = dialog.findViewById(R.id.connectButton)

        val window = dialog.window
        val layoutParams = window?.attributes
        layoutParams?.width = WindowManager.LayoutParams.MATCH_PARENT
        layoutParams?.height = WindowManager.LayoutParams.WRAP_CONTENT
        layoutParams?.horizontalMargin = 20F
        window?.attributes = layoutParams

        val networkSSID = wifiNetworks[networkPosition].ssid
        val tvEnterPasswordForNetwork: TextView = dialog.findViewById(R.id.tvEnterPassword)
        val tvWithNetworkSSID = tvEnterPasswordForNetwork.text.toString() + networkSSID
        val editTextEnterPassword: EditText = dialog.findViewById(R.id.editTextWifiPassword)
        tvEnterPasswordForNetwork.text = tvWithNetworkSSID

        dialogCancelBtn.setOnClickListener {
            dialog.dismiss()
        }
        dialogConnectBtn.setOnClickListener {
            val network = MainActivity.db.dao().getNetwork(networkSSID)
            if (network == null) {
                tvEnterPasswordForNetwork.text = "Cannot connect"
                Log.e("Connect", "Cannot connect to wifi network $networkSSID")
            } else {
                if (editTextEnterPassword.text.toString() == network.password) {
                    MainActivity.db.dao().updateNetworkConnectionStatus(true)
                    Log.e("Connect", "Connected to wifi network $networkSSID")
                    dialog.dismiss()
                } else {
                    tvEnterPasswordForNetwork.text = "Incorrect password"
                    Log.e("Connect", "Incorrect password")
                }
            }
        }

        dialog.show()
    }
}