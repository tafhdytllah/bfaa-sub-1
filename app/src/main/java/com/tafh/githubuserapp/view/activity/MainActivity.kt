package com.tafh.githubuserapp.view.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.tafh.githubuserapp.R
import com.tafh.githubuserapp.databinding.ActivityMainBinding
import com.tafh.githubuserapp.view.fragment.ListUserFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mFragmentManager = supportFragmentManager
        val mListUserFragment = ListUserFragment()
        val fragment = mFragmentManager.findFragmentByTag(ListUserFragment::class.java.simpleName)

        if (fragment !is ListUserFragment) {
            mFragmentManager
                .beginTransaction()
                .add(R.id.frame_container, mListUserFragment, ListUserFragment::class.java.simpleName)
                .commit()
        }
    }
}