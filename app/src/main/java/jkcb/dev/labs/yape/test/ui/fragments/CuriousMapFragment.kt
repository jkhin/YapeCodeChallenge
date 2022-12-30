package jkcb.dev.labs.yape.test.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import jkcb.dev.labs.yape.test.R

class CuriousMapFragment : Fragment() {

    private val args: CuriousMapFragmentArgs by navArgs()

    private val callback = OnMapReadyCallback { googleMap ->
        with(args) {
            val latLng = LatLng(authorLat.toDouble(), authorLng.toDouble())
            googleMap.uiSettings.isZoomControlsEnabled = true
            googleMap.addMarker(MarkerOptions().position(latLng).title("Author's location"))
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10.0f))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_curious_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}