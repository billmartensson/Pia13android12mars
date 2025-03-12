package se.magictechnology.pia13android12mars

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PosViewModel : ViewModel() {

    lateinit var fusedLocationClient: FusedLocationProviderClient

    private val _lastpos : MutableStateFlow<Location?> = MutableStateFlow(null)
    val lastpos: StateFlow<Location?> = _lastpos.asStateFlow()


    @RequiresPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
    fun getpos() {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location : Location? ->
                // Got last known location. In some rare situations this can be null.
                if(location == null) {
                    Log.i("PIA13DEBUG", "NO LOCATION")
                } else {
                    _lastpos.value = location
                    Log.i("PIA13DEBUG", "LOCATION: ${location.latitude} ${location.longitude}")
                }
            }
    }
}