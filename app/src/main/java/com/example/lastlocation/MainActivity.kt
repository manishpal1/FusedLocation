package com.example.lastlocation


import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    private lateinit var fusedLocationProviderClient:FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initLocationProviderClient()
        setUoButtonClickLIstner()
    }

    private fun setUoButtonClickLIstner() {
        button.setOnClickListener {
         getUserLocation()
}
    }

    private fun getUserLocation() {
        if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),1)
            return
        }else{
            fusedLocationProviderClient.lastLocation.addOnSuccessListener { location: Location?->
                latTextView.text="Latitude:"+location?.latitude
                lngTextView.text="Longitude"+location?.longitude
            }

        }
    }

    private fun initLocationProviderClient() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
    }

}




