package com.sidukov.descriptionbin.descriptionbin.ui

import android.graphics.Rect
import android.view.View
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView

class EmptyDividerItemDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        parent.children.forEachIndexed { index, child ->
            when (index) {
                0 -> {
                    child.setPadding(0, 0, 0, 10)
                }
                parent.childCount - 1 -> {
                    child.setPadding(0, 0, 0, 10)
                }
                else -> {
                    child.setPadding(0, 0, 0, 0)
                }
            }
        }
    }
}