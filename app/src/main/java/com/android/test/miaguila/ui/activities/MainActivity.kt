package com.android.test.miaguila.ui.activities

import android.content.res.Resources.NotFoundException
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.test.miaguila.R
import com.android.test.miaguila.databinding.ActivityMainBinding
import com.android.test.miaguila.networking.data.RouteData
import com.android.test.miaguila.utils.Utils
import com.android.test.miaguila.viewmodels.MainViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.include_route_summary.view.*

class MainActivity : AppCompatActivity(), OnMapReadyCallback {


    lateinit var binding: ActivityMainBinding
    lateinit var viewModel : MainViewModel

    lateinit var googleMap : GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        setupMapFragment()
        initViewModel()
    }

    private fun setupMapFragment() {
        val mapFragment = map as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this)[MainViewModel::class.java]
        viewModel.liveDataRoute.observe(this, Observer {
            updateMapWithRoute(it)
            updateSummaryRoute(it)
        })
        viewModel.getRoute()
    }

    private fun updateSummaryRoute(route: RouteData.Route) {
        binding.includeCardView.includeSummaryRoute.apply {
            this.route_summary_start.setText(getString(R.string.from,route.startAddress))
            this.route_summary_start_time.setText(getString(R.string.from_time,route.startTime))
            this.route_summary_end.setText(getString(R.string.until,route.endAddress))
            this.route_summary_end_time.setText(getString(R.string.until_time,route.endTime))
        }
    }

    override fun onMapReady(map: GoogleMap) {
        this.googleMap = map
        try {
            googleMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                    this, R.raw.google_maps_style
                )
            )
        } catch (e: NotFoundException) {
            e.printStackTrace()
        }
    }

    private fun updateMapWithRoute(route : RouteData.Route){
        if(route.startLocation.isNotEmpty() && route.endLocation.isNotEmpty()){

            val startMarkerOptions = MarkerOptions()
            val startAddressLocation = LatLng(route.startLocation[0], route.startLocation[1])
            startMarkerOptions.position(startAddressLocation)
            startMarkerOptions.icon(BitmapDescriptorFactory.fromBitmap(createMarker(R.drawable.circle_start_point, route.startAddress)))
            googleMap.addMarker(startMarkerOptions)

            val endMarkerOptions = MarkerOptions()
            val endAddressLocation = LatLng(route.endLocation[0], route.endLocation[1])
            endMarkerOptions.position(endAddressLocation)
            endMarkerOptions.icon(BitmapDescriptorFactory.fromBitmap(createMarker(R.drawable.circle_end_point, route.endAddress)))
            googleMap.addMarker(endMarkerOptions)

            val bounds = LatLngBounds.builder()
            bounds.include(startAddressLocation)
            bounds.include(endAddressLocation)
            val center = bounds.build().center
            val cu = CameraUpdateFactory.newLatLngZoom(center, 15f)
            val paddingBottom = binding.cardContainer.height - binding.cardContainer.paddingTop
            val paddingLeft = binding.cardContainer.paddingStart
            googleMap.addPolyline(
                PolylineOptions()
                    .add(startAddressLocation)
                    .add(endAddressLocation)
                    .color(R.color.maBlueDark)
            )
            googleMap.setPadding(paddingLeft,0,0,paddingBottom)
            googleMap.animateCamera(cu)
        }
    }

    private fun createMarker(backgroundResource : Int, label : String ) : Bitmap{
        val markerView = LayoutInflater.from(this).inflate(R.layout.custom_marker_view, null, false)
        val imageMarker = markerView.findViewById<ImageView>(R.id.imageMarker)
        val labelMarker = markerView.findViewById<TextView>(R.id.labelMarker)
        labelMarker.text = label
        imageMarker.setBackgroundResource(backgroundResource)
        return Utils.convertViewToDrawable( markerView)
    }
}
