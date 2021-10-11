package com.example.permission

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.permission.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    var CODE = 2
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onClickButton()
    }
    private fun onClickButton(){
        binding.button.setOnClickListener(){
            requestCameraPermission()
        }
    }

    private fun requestCameraPermission(){
        val permission = ContextCompat.checkSelfPermission(this,android.Manifest.permission.CAMERA)
        if (permission==PackageManager.PERMISSION_GRANTED){
            startCamera()
        }else{if (ActivityCompat.shouldShowRequestPermissionRationale(this,android.Manifest.permission.CAMERA)){
            showCustomDialog()
        }else{
            requestPermissions(arrayOf(android.Manifest.permission.CAMERA),CODE)
        }
    }

}

    private fun showCustomDialog() {
        MaterialAlertDialogBuilder(this).setTitle(getString(R.string.attention)).setMessage("CONCEDI PERMESSI")
    }

    private fun startCamera(){
        Snackbar.make(binding.root,getString(R.string.fotocamera),Snackbar.LENGTH_LONG).show()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            CODE->{
                if (grantResults.isNotEmpty()&&grantResults.first()==PackageManager.PERMISSION_GRANTED){
                    startCamera()
                }
            }
        }

    }

}






