package com.example.testopencv

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.util.Util
import com.example.testopencv.MainActivity.Companion.frameList
import org.opencv.android.Utils
import org.opencv.core.CvException
import org.opencv.core.Mat
import org.opencv.imgproc.Imgproc


class FramesAdapter(private val context: Context): RecyclerView.Adapter<FramesAdapter.MyImageHolder>() {


    class MyImageHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: AppCompatImageView = itemView.findViewById(R.id.imageIv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyImageHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_image_layout, parent, false)
        return MyImageHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyImageHolder, position: Int) {

        val bmp: Bitmap? = null
        Utils.matToBitmap(frameList[position], bmp)

        Glide.with(context).load(bmp).apply(
            RequestOptions.centerCropTransform().dontAnimate()
        ).thumbnail(0.5f).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return frameList.size
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