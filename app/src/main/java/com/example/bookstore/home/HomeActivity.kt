package com.example.bookstore.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bookstore.R
import com.example.bookstore.extensions.inTransaction
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        supportFragmentManager.inTransaction {
            replace(R.id.container, HomeFragment.newInstance())
        }
    }
}