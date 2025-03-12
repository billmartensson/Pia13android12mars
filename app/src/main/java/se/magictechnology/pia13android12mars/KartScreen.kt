package se.magictechnology.pia13android12mars

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun KartScreen(posviewmodel : PosViewModel = viewModel()) {

    val singapore = LatLng(1.35, 103.87)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 10f)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Text("POSITION")

        GoogleMap(
            modifier = Modifier.height(300.dp).width(300.dp),
            cameraPositionState = cameraPositionState
        )
    }


}

@Preview(showBackground = true)
@Composable
fun KartScreenPreview() {
    KartScreen()
}