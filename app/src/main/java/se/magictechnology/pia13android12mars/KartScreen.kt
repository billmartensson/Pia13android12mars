package se.magictechnology.pia13android12mars

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.AdvancedMarker
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun KartScreen(posviewmodel : PosViewModel) {

    val lastpos by posviewmodel.lastpos.collectAsState()

    val singapore = LatLng(1.35, 103.87)
    val singapore2 = LatLng(1.36, 103.88)

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 12f)
    }

    LaunchedEffect(lastpos) {
        if(lastpos != null) {
            cameraPositionState.position = CameraPosition.fromLatLngZoom(LatLng(lastpos!!.latitude, lastpos!!.longitude), 15f)
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Text("POSITION")

        if(lastpos == null) {
            Text("NO POSITION")
        } else {
            Text("LOCATION: ${lastpos!!.latitude} ${lastpos!!.longitude}")
        }

        Button(onClick = {
            posviewmodel.getpos()
        }) {
            Text("GET POSITION")
        }

        GoogleMap(
            modifier = Modifier.fillMaxWidth().weight(1f),
            cameraPositionState = cameraPositionState
        ) {
            AdvancedMarker(
                state = MarkerState(position = LatLng(-34.0, 151.0)),
                title = "Marker in Sydney",
                onClick = {
                    Log.i("PIA13DEBUG", "KLICK PÅ SYDNEY")
                    it.showInfoWindow()
                    true
                }
            )
            AdvancedMarker(
                state = MarkerState(position = LatLng(35.66, 139.6)),
                title = "Marker in Tokyo"
            )

            Polyline(
                points = listOf(singapore, singapore2)
            )

        }
    }


}

@Preview(showBackground = true)
@Composable
fun KartScreenPreview() {
    KartScreen(viewModel())
}