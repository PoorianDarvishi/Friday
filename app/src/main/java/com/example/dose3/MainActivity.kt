package com.example.dose3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.viewModels
import androidx.fragment.app.commit
import androidx.fragment.app.add
import androidx.fragment.app.activityViewModels

class MainActivity : AppCompatActivity() {
    private val mainViewModel : MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (!mainViewModel.flag){
            mainViewModel.flag = true
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<HomePageFragment>(R.id.fragment_container_view)
            }
        }

    }
}