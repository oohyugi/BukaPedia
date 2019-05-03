package com.oohyugi.bukasempak.view.home.slider

import android.content.Context
import android.database.DataSetObserver
import android.os.Parcelable
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup

/**
 * Created by oohyugi on 2019-04-24.
 * github: https://github.com/oohyugi
 */
class InfiniteViewPager @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ViewPager(context, attrs) {
    // Allow for 100 back cycles from the beginning.
    // This should be enough to create an illusion of infinity.
    // Warning: scrolling to very high values (1,000,000+) results in strange drawing behaviour.
    private val offsetAmount get() = if (adapter?.count == 0) 0 else (adapter as InfinitePagerAdapter).realCount * 100

    override fun setAdapter(adapter: PagerAdapter?) {
        super.setAdapter(if (adapter == null) null else InfinitePagerAdapter(adapter))
        currentItem = 0
    }

    override fun setCurrentItem(item: Int) = setCurrentItem(item, false)

    override fun setCurrentItem(item: Int, smoothScroll: Boolean) {
        val adapterCount = adapter?.count

        if (adapterCount == null || adapterCount == 0) {
            super.setCurrentItem(item, smoothScroll)
        } else {
            super.setCurrentItem(offsetAmount + item % adapterCount, smoothScroll)
        }
    }

    override fun getCurrentItem(): Int {
        val adapterCount = adapter?.count

        return if (adapterCount == null || adapterCount == 0) {
            super.getCurrentItem()
        } else {
            val position = super.getCurrentItem()
            position % (adapter as InfinitePagerAdapter).realCount
        }
    }

    fun animateForward() {
        super.setCurrentItem(super.getCurrentItem() + 1, true)
    }

    fun animateBackwards() {
        super.setCurrentItem(super.getCurrentItem() - 1, true)
    }

    internal class InfinitePagerAdapter(private val adapter: PagerAdapter) : PagerAdapter() {
        internal val realCount: Int get() = adapter.count

        override fun getCount() = if (realCount == 0) 0 else Integer.MAX_VALUE

        override fun instantiateItem(container: ViewGroup, position: Int) = adapter.instantiateItem(container, position % realCount)

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) = adapter.destroyItem(container, position % realCount, `object`)

        override fun finishUpdate(container: ViewGroup) = adapter.finishUpdate(container)

        override fun isViewFromObject(view: View, `object`: Any) = adapter.isViewFromObject(view, `object`)

        override fun restoreState(bundle: Parcelable?, classLoader: ClassLoader?) = adapter.restoreState(bundle, classLoader)

        override fun saveState(): Parcelable? = adapter.saveState()

        override fun startUpdate(container: ViewGroup) = adapter.startUpdate(container)

        override fun getPageTitle(position: Int) = adapter.getPageTitle(position % realCount)

        override fun getPageWidth(position: Int) = adapter.getPageWidth(position)

        override fun setPrimaryItem(container: ViewGroup, position: Int, `object`: Any) = adapter.setPrimaryItem(container, position, `object`)

        override fun unregisterDataSetObserver(observer: DataSetObserver) = adapter.unregisterDataSetObserver(observer)

        override fun registerDataSetObserver(observer: DataSetObserver) = adapter.registerDataSetObserver(observer)

        override fun notifyDataSetChanged() = adapter.notifyDataSetChanged()

        override fun getItemPosition(`object`: Any) = adapter.getItemPosition(`object`)
    }
}