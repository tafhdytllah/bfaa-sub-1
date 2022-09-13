package com.tafh.githubuserapp.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.tafh.githubuserapp.R
import com.tafh.githubuserapp.data.model.UserData
import com.tafh.githubuserapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}