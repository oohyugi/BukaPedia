package com.oohyugi.bukasempak.utils

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View


class MarginItemDecoration(private val space: Int,val type:Int=1) : RecyclerView.ItemDecoration() {

    companion object{
        const val TYPE_VERTICAL  =1
        const val TYPE_GRID_VERTICAL  =3
        const val TYPE_HORIZONTAL  =2
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


        }
    }
}