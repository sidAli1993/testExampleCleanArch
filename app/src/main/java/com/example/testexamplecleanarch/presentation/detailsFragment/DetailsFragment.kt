package com.example.testexamplecleanarch.presentation.detailsFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.testexamplecleanarch.R
import com.example.testexamplecleanarch.databinding.FragmentDetailsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMapLoadedCallback
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import kotlin.math.ln


class DetailsFragment : Fragment(), OnMapReadyCallback, OnMapLoadedCallback {
    private var mMap: GoogleMap? = null
    var lat=0.0
    var lng=0.0
    private var bounds: LatLngBounds? = null
    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding
        get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)

        arguments?.let {
            lat=it.getDouble("lat")
            lng=it.getDouble("lng")
        }
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = requireActivity().supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(p0: GoogleMap) {
        mMap = p0
        mMap!!.uiSettings.isZoomControlsEnabled = true
        mMap!!.setOnMapLoadedCallback(this)
        val marker = MarkerOptions().position(LatLng(lat, lng)).title("Bike")
        mMap?.addMarker(marker)
        bounds = LatLngBounds(LatLng(lat, lng), LatLng(lat,lng))
        mMap!!.moveCamera(CameraUpdateFactory.newLatLng(LatLng(lat, lng)))
        mMap!!.animateCamera(
            CameraUpdateFactory.newLatLngZoom(
                LatLng(
                    lat,
                    lng
                ), 12.0f
            )
        )
    }

    override fun onMapLoaded() {

        mMap!!.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds!!, 170))

    }

}