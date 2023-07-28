package com.example.testopencv

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.annotation.BinderThread
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import org.opencv.android.OpenCVLoader
import org.opencv.android.Utils
import org.opencv.core.CvException
import org.opencv.core.Mat
import org.opencv.imgproc.Imgproc
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var openCamView : Button
    private lateinit var imageView : ImageView


    @SuppressLint("MissingInflatedId")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(OpenCVLoader.initDebug()) Log.d("MainActivity", "Loaded!")
        getPermission()

        openCamView = findViewById(R.id.OpenCamButton)
        imageView = findViewById(R.id.imageView)


        openCamView.setOnClickListener {
            val openCvIntent = Intent(this@MainActivity, OpenCVActivity::class.java)
            startActivity(openCvIntent)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun getPermission() {
        if(checkSelfPermission(android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(arrayOf(android.Manifest.permission.CAMERA), 101)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(grantResults.isNotEmpty() && grantResults[0] != PackageManager.PERMISSION_GRANTED)
            getPermission()
    }

    /*override fun onResume() {
        super.onResume()
        Log.d("OnResumeFun", "On Resumed Called")
        Log.d("OnResumeFun", "${frameList.size}")

        if(!frameList.isEmpty()) {
            Log.d("OnResumeFun", "Frame List Not Empty!!!")
            val bmp: Bitmap = Bitmap.createBitmap(frameList[0].cols(), frameList[0].rows(), Bitmap.Config.ARGB_8888)
            Utils.matToBitmap(frameList[0], bmp)
            imageView.setImageBitmap(bmp)
        }
    }*/

    companion object {
        var frameList : ArrayList<Mat> = ArrayList()
    }

    private fun convertMatToBitMap(input: Mat): Bitmap? {
        var bmp: Bitmap? = null
        val rgb = Mat()
        Imgproc.cvtColor(input, rgb, Imgproc.COLOR_BGR2RGB)
        try {
            bmp = Bitmap.createBitmap(rgb.cols(), rgb.rows(), Bitmap.Config.ARGB_8888)
            Utils.matToBitmap(rgb, bmp)
        } catch (e: CvException) {
            Log.d("Exception", e.message.toString())
        }
        return bmp
    }
}