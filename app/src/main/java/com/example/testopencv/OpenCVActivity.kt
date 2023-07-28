package com.example.testopencv

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Camera
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.testopencv.MainActivity.Companion.frameList
import org.opencv.android.CameraActivity
import org.opencv.android.CameraBridgeViewBase
import org.opencv.android.OpenCVLoader
import org.opencv.core.Mat
import java.text.SimpleDateFormat
import java.util.*

class OpenCVActivity : CameraActivity() {

    private lateinit var cameraView : CameraBridgeViewBase

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_cv)

        cameraView = findViewById(R.id.cam_view)
        cameraView.setMaxFrameSize(500, 500)

        val currentTime = getCurrentTime()

        if(OpenCVLoader.initDebug()) cameraView.enableView()
        else Log.d("MainActivity", "Wrong")

//        Log.d("supportedPreviewFps", CustomizableCameraView(this, 0).getFPSRange().toString())

        cameraView.setCvCameraViewListener(object : CameraBridgeViewBase.CvCameraViewListener2 {
            override fun onCameraViewStarted(width: Int, height: Int) {}

            override fun onCameraViewStopped() {
                Log.d("MainActivity", "${getCurrentTime()} - $currentTime")
                Log.d("MainActivity", frameList.size.toString())
//                val framesIntent = Intent(this@OpenCVActivity, FramesActivity::class.java)
//                startActivity(framesIntent)
            }
            override fun onCameraFrame(inputFrame: CameraBridgeViewBase.CvCameraViewFrame): Mat {
                frameList.add(inputFrame.gray())
                Log.d("Height", "${inputFrame.gray().height()}")
                Log.d("Width", "${inputFrame.gray().width()}")
                Log.d("Size", "${inputFrame.gray().size()}")

                return inputFrame.gray()
            }
        })
    }

    override fun getCameraViewList(): MutableList<out CameraBridgeViewBase> {
        return Collections.singletonList(cameraView)
    }

    override fun onResume() {
        super.onResume()
        cameraView.enableView()
    }

    override fun onPause() {
        super.onPause()
        cameraView.disableView()
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraView.disableView()
    }

    @SuppressLint("SimpleDateFormat")
    fun getCurrentTime(): String {
        val calendar = Calendar.getInstance()
        val mdformat = SimpleDateFormat("HH:mm:ss")
        return mdformat.format(calendar.time)
    }
}

//D/MainActivity: 11:44:10 - 11:44:07
//D/MainActivity: 48

//D/MainActivity: 11:45:13 - 11:45:09
//D/MainActivity: 69