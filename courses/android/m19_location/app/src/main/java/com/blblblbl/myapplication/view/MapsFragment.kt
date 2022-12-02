package com.blblblbl.myapplication.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.blblblbl.myapplication.R
import com.blblblbl.myapplication.databinding.DialogPlaceInfoBinding
import com.blblblbl.myapplication.databinding.FragmentMapsBinding
import com.blblblbl.myapplication.presentation.MapsViewModel
import com.bumptech.glide.Glide
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MapsFragment : Fragment() {
    lateinit var binding: FragmentMapsBinding
    private val viewModel:MapsViewModel by viewModels()
    private lateinit var googleMap1:GoogleMap
    private lateinit var bottomSheetDialog :BottomSheetDialog
    private lateinit var dialogBinding:DialogPlaceInfoBinding
    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        with(googleMap.uiSettings){
            isZoomControlsEnabled = true
            isMyLocationButtonEnabled = true
        }

        googleMap.isMyLocationEnabled = true
        var fusedLocationClient = context?.let { LocationServices.getFusedLocationProviderClient(it) }
        var location = fusedLocationClient?.getLastLocation()
        if (location != null) {
            location.addOnCompleteListener{
                if (it.isSuccessful){
                    val myLoc = LatLng(location?.result!!.latitude, location.result.longitude)
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLoc,15f))
                }
            }
        }
        googleMap.setOnCameraIdleListener {
            var a = googleMap1.cameraPosition.target
            val bounds: LatLngBounds = googleMap1.getProjection().getVisibleRegion().latLngBounds
            viewModel.buttonOnClick(lon_min=bounds.southwest.longitude,lat_min=bounds.southwest.latitude,lon_max=bounds.northeast.longitude,lat_max=bounds.northeast.latitude)

        }
        googleMap.setOnMarkerClickListener { marker ->
            if (marker.isInfoWindowShown) {
                marker.hideInfoWindow()
            } else {
                marker.showInfoWindow()
            }
            marker.snippet?.let {
                viewModel.markerOnClick(it)
                bottomSheetDialog.show()
            }
            true
        }
        googleMap1=googleMap
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMapsBinding.inflate(layoutInflater)

        bottomSheetDialog = BottomSheetDialog(this.requireContext())
        dialogBinding = DialogPlaceInfoBinding.inflate(layoutInflater)
        bottomSheetDialog.setContentView(dialogBinding.root)

       /* binding.bPlaces.setOnClickListener {
            var a = googleMap1.cameraPosition.target
            val bounds: LatLngBounds = googleMap1.getProjection().getVisibleRegion().latLngBounds
            viewModel.buttonOnClick(lon_min=bounds.southwest.longitude,lat_min=bounds.southwest.latitude,lon_max=bounds.northeast.longitude,lat_max=bounds.northeast.latitude)

        }*/
        lifecycleScope.launchWhenStarted {
            viewModel.places.collect{places->
                Log.d("MyLog","maps fragment${places.toString()}")
                if (::googleMap1.isInitialized) {googleMap1.clear()}
                places?.forEach{place ->
                    val pos = LatLng(place.point?.lat!!,place.point?.lon!!)
                    googleMap1.addMarker(
                        MarkerOptions()
                            .position(pos)
                            .snippet(place.xid)
                            .title("${place.name}"))
                    var a = googleMap1.cameraPosition.target
                }

            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.placeWithInfo.collect{
                place->
                val regex = "</?.*?>".toRegex()
                dialogBinding.tvDescription.text = place?.info?.descr?.replace(regex,"")
                Glide.with(dialogBinding.ivMain.context).load(place?.image).into(dialogBinding.ivMain)
                Log.d("MyLog",place?.info.toString())
            }
        }
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}