package com.oohyugi.bukasempak.view.detail

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.oohyugi.bukasempak.model.ImagesMdl

/**
 * Created by oohyugi on 2019-05-05.
 * github: https://github.com/oohyugi
 */
class ImgProductPagerAdapter(fm: FragmentManager, val list:List<String>) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return ImageFragment.newInstance(list[position])
    }

    override fun getCount(): Int {
        return list.size
    }

}