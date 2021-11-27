package com.justnik.shoppinglistclear.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.justnik.shoppinglistclear.R
import com.justnik.shoppinglistclear.domain.ShopItem

class MainActivity : AppCompatActivity() {

    companion object{
        private const val TAG = "MainActivityTest"
    }

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mainViewModel.shopList.observe(this){
            Log.d(TAG, it.toString())
            Log.d(TAG, "\n")
        }


    }
}