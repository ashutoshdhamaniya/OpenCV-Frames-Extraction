package com.example.testopencv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class FramesActivity : AppCompatActivity() {

    private lateinit var recyclerView : RecyclerView
    private lateinit var framesAdapter: FramesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frames)

        recyclerView = findViewById(R.id.recyclerview)

        val layoutManager =StaggeredGridLayoutManager(3,OrientationHelper.VERTICAL)
        layoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
        recyclerView.layoutManager = layoutManager

        framesAdapter = FramesAdapter(this)
        recyclerView.adapter = framesAdapter

        val dividerItemDecorationVertical = DividerItemDecoration(
            recyclerView.context,
            LinearLayout.HORIZONTAL
        )

        val dividerItemDecorationHorizontal = DividerItemDecoration(
            recyclerView.context,
            LinearLayout.VERTICAL
        )
        recyclerView.addItemDecoration(dividerItemDecorationVertical)
        recyclerView.addItemDecoration(dividerItemDecorationHorizontal)
    }
}