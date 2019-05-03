package com.oohyugi.bukasempak.view.home.slider


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide

import com.oohyugi.bukasempak.R
import com.oohyugi.bukasempak.model.ItemsMdl
import kotlinx.android.synthetic.main.slider_fragment.*
import android.R.attr.src
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.support.v4.graphics.drawable.RoundedBitmapDrawable
import com.oohyugi.bukasempak.model.BannerMdl


class SliderFragment : Fragment() {
   var itemMdl: BannerMdl? = null
      val ARG_ITEM = "item"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            itemMdl = it.getSerializable(ARG_ITEM) as BannerMdl?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.slider_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        Glide.with(view).load(itemMdl!!.image.newMobileUrl).into(imgSlider)
    }


    companion object {

        @JvmStatic
        fun newInstance(item: BannerMdl?) =
            SliderFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_ITEM, item)
                }
            }
    }
}
