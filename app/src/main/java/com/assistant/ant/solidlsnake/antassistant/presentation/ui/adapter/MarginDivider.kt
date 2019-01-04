package com.assistant.ant.solidlsnake.antassistant.presentation.ui.adapter

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View
import com.assistant.ant.solidlsnake.antassistant.dpToPx

class MarginDivider(margin: Int) : RecyclerView.ItemDecoration() {
    private val marginInPx: Int = margin.dpToPx()

    var a: Int = 0

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val i = marginInPx + a
        outRect.bottom = i
    }
}