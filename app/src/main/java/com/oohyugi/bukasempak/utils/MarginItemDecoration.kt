package com.oohyugi.bukasempak.utils

import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView
import android.view.View




class MarginItemDecoration(private val space: Int,val type:Int=1) : RecyclerView.ItemDecoration() {

    companion object{
        const val TYPE_VERTICAL  =1
        const val TYPE_GRID_VERTICAL  =3
        const val TYPE_HORIZONTAL  =2
        const val TYPE_GRID_HORIZONTAL  =4
    }
    override fun getItemOffsets(outRect: Rect, view: View,
                                parent: RecyclerView, state: RecyclerView.State) {
        with(outRect) {
            if (type == TYPE_VERTICAL){
                if (parent.getChildAdapterPosition(view) == 0) {
                    top = space
                }
//            left =  spaceHeight
//            right = spaceHeight
                bottom = space
            }

            if (type == TYPE_HORIZONTAL){
                if (parent.childCount != parent.childCount-1) {
                    right = space
                }

                if (parent.getChildAdapterPosition(view) == 0) {
                    left = space
                }
//
            }

            if (type == TYPE_GRID_VERTICAL){
                if (parent.getChildAdapterPosition(view) != 0) {
                    top = space
                }
//            left =  spaceHeight
//            right = spaceHeight
//                bottom = space
            }
            if (type == TYPE_GRID_HORIZONTAL){
                val position = parent.getChildAdapterPosition(view) // item position
                val column = position % 2 // item column
                outRect.left = column * space / 2; // column * ((1f / spanCount) * spacing)
                outRect.right = space - (column + 1) * space / 2; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= 2) {
                    outRect.top = space; // item top
                }
            }


        }
    }
}