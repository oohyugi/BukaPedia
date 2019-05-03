package com.oohyugi.bukasempak.utils.viewpager

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

import java.util.ArrayList

/**
 * Created by oohyugi on 2019-04-27.
 * github: https://github.com/oohyugi
 */
internal abstract class CircularFragmentPagerAdapter(fragmentManager: FragmentManager, count: Int) :
    FragmentStatePagerAdapter(fragmentManager) {
    private val mPageList: MutableList<Int>

    init {
        mPageList = ArrayList()
        mPageList.add(count + 1)
        for (index in 0 until count) {
            mPageList.add(index + 1)
        }
        mPageList.add(0)
    }

    override fun getCount(): Int {
        return if (mPageList.size > 2) mPageList.size else 0
    }

    override fun getItem(position: Int): Fragment {
        if (position == mPageList.size - 1) {
            return getFragment(0)
        } else if (position == 0) {
            return getFragment(mPageList.size - 3)
        }
        return getFragment(mPageList[position] - 1)
    }

    protected abstract fun getFragment(position: Int): Fragment
}