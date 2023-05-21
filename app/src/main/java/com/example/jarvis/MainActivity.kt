package com.example.jarvis

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.jarvis.assistant.AssistantActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var imageActionButton: ImageButton
    val RecordAudioRequestCode: Int = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getSupportActionBar()?.setBackgroundDrawable(ColorDrawable(getResources().getColor(R.color.blue)));

        imageActionButton = findViewById(R.id.action_button)

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.RECORD_AUDIO)
            != PackageManager.PERMISSION_GRANTED
        ) {
            checkPermission()
        }

        imageActionButton.setOnClickListener {
            startActivity(Intent(this, AssistantActivity::class.java))
        }
    }

        override fun onRequestPermissionsResult(
            requestCode: Int,
            permission: Array<out String>,
            grantResult: IntArray
        ) {
            super.onRequestPermissionsResult(requestCode, permission, grantResult)
            if (requestCode == RecordAudioRequestCode && grantResult.size > 0) {
                if (grantResult[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
                }
            }
        }


        private fun checkPermission(){
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.RECORD_AUDIO),
                RecordAudioRequestCode)
            }
        }

    }

