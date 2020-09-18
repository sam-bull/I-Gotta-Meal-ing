package com.example.bink.igottamealing.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bink.igottamealing.R
import com.example.bink.igottamealing.view.fragment.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container,
                    MainFragment.newInstance()
                )
                .commitNow()
        }
    }
}
