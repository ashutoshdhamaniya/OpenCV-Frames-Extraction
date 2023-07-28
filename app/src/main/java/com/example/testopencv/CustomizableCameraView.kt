package com.example.testopencv

import android.content.Context
import android.hardware.Camera
import android.util.AttributeSet
import org.opencv.android.JavaCameraView


class CustomizableCameraView(context: Context?, cameraId: Int) :
    JavaCameraView(context, cameraId) {

    fun setPreviewFPS(min: Double, max: Double) {
        val params: Camera.Parameters = mCamera.parameters
        params.setPreviewFpsRange((min * 1000).toInt(), (max * 1000).toInt())
        mCamera.parameters = params
    }

    fun getFPSRange(): MutableList<IntArray>? {
        val params: Camera.Parameters = mCamera.parameters
        return params.supportedPreviewFpsRange
    }
}