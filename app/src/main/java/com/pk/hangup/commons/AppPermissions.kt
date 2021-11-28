package com.pk.hangup.commons

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class AppPermissions {
    companion object{
        fun readExtStorage(context: Context,activity:Activity)
        {
            if(ContextCompat.checkSelfPermission(context,
                Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED)
            else
            {
                var REQUEST_CODE = 0
                val array:Array<String> = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                ActivityCompat.requestPermissions(activity,array,REQUEST_CODE)
            }
        }
    }
}