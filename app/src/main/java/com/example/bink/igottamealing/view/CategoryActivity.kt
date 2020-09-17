package com.example.bink.igottamealing.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bink.igottamealing.R

class CategoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CategoryFragment.newInstance())
                .commitNow()
        }
    }
}
