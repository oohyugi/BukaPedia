package com.oohyugi.bukasempak.view.home.slider

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import android.view.View
import com.oohyugi.bukasempak.model.ItemsMdl
import android.view.ViewGroup
import com.oohyugi.bukasempak.model.BannerMdl
import android.widget.LinearLayout




/**
 * Created by oohyugi on 2019-04-24.
 * github: https://github.com/oohyugi
 */
class SliderPagerAdapter(var list:List<BannerMdl>) : PagerAdapter() {
    override fun isViewFromObject(p0: View, p1: Any): Boolean {
        return p0 == p1 as LinearLayout
    }

    //    var mFrags: MutableList<Fragment> = mutableListOf()
//    var mFragMan: FragmentManager? = null
//    init {
//        for (item in list){
//            mFrags.add(SliderFragment.newInstance(item))
//        }
//        mFragMan = fm
//
//
//    }
//    override fun getItem(p0: Int): Fragment {
//
//        return SliderFragment.newInstance(list[p0])
//
//    }

    override fun getCount(): Int {
        // Since we want to put 2 additional pages at left & right,
        // the actual size will plus 2.
        return if (list.isEmpty()) 0 else list.size + 2
    }
    fun getRealCount(): Int {
        return list.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val  modelPosition = mapPagerPositionToModelPosition(position)

        val  model = list[modelPosition]

        return  SliderFragment.newInstance(model)
    }


    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }

    private fun mapPagerPositionToModelPosition(pagerPosition: Int): Int {
        // Put last page model to the first position.
        if (pagerPosition == 0) {
            return getRealCount() - 1
        }
        // Put first page model to the last position.
        return if (pagerPosition == getRealCount() + 1) {
            0
        } else pagerPosition - 1
    }

}

