package org.inu.iot.iotproject.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.inu.iot.iotproject.R
import org.inu.iot.iotproject.adapter.AdapterRecyclerSanitizer

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var adapterRecyclerSanitizer : AdapterRecyclerSanitizer = AdapterRecyclerSanitizer()

        recyclerView.layoutManager = GridLayoutManager(applicationContext, 2)
        recyclerView.adapter = adapterRecyclerSanitizer
    }
}