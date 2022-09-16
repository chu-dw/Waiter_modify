package com.tukorea.waiter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tukorea.waiter.databinding.ActivityMapPageBinding
import net.daum.mf.map.api.MapView


class Map_page : AppCompatActivity() {
    private lateinit var binding: ActivityMapPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_page)

        binding = ActivityMapPageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val map = MapView(this)
        binding.mapView.addView(map)
//        val mapView = MapView(this)
//        binding.clKakaoMapView.addView(mapView)

    }
}