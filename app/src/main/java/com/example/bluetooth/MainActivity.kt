package com.example.bluetooth

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.bluetooth.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private  lateinit var binding: ActivityMainBinding
    lateinit var bluetooth:BluetoothAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getAction = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){

        }

        if(bluetooth.isEnabled){
            binding.icon.setImageResource(R.drawable.bluetooth_On)
        }
        else{
            binding.icon.setImageResource(R.drawable.bluetooth_On)
        }

        binding.button.setOnClickListener {
            if (bluetooth.isEnabled){
                Toast.makeText(this,"already on",Toast.LENGTH_LONG).show()
            }
            else{
                var intent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivityForResult(intent,REQUEST_CODE_BT)
            }
        }

        binding.button2.setOnClickListener {
            if (!bluetooth.isEnabled){
                Toast.makeText(this,"already off",Toast.LENGTH_LONG).show()
            }
            else{
                bluetooth.disable()
                binding.icon.setImageResource(R.drawable.bluetooth_Off)
            }
        }

        binding.button4.setOnClickListener {
            if (bluetooth.isEnabled){
                binding.textView2.text = "paired devices:"
                val devices = bluetooth.bondedDevices
                for (device in devices){
                    val deviceName = device.name
                    val deviceAddress = device
                    binding.textView2.append("\nDevice:$deviceName, $device")
                }
            }
            else{
                Toast.makeText(this,"turn on bluetooth",Toast.LENGTH_LONG).show()
            }
        }
    }


}