package com.oohyugi.bukasempak.view.home.banner


import android.content.Context
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.kingfisher.easyviewindicator.AnyViewIndicator
import com.oohyugi.bukasempak.R
import com.oohyugi.bukasempak.model.BannerMdl
import com.oohyugi.bukasempak.utils.MarginItemDecoration

class ViewHolderBanner(itemView: View, context: Context) : RecyclerView.ViewHolder(itemView) {

    fun setItem(list: MutableList<BannerMdl>) {
        mListBanner.clear()
        mListBanner.addAll(list)

        if (mListBanner.isNotEmpty()){
            lyHome.visibility = View.VISIBLE
        }
        mBannerAdapter.notifyDataSetChanged()
        if (mListBanner.size>1){
            autoScroll(rvBanner, firstItemVisible)
        }

    }

    val mListBanner: MutableList<BannerMdl> = mutableListOf()
    var mBannerAdapter: BannerAdapter
    var tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
    var lyContainer: LinearLayout = itemView.findViewById(R.id.ly_container)
    var lyHome: RelativeLayout = itemView.findViewById(R.id.ly_home)
    val rvBanner: RecyclerView
    val indicator: AnyViewIndicator
    var firstItemVisible = 0
    var firstCompletelyItemVisible = 0
    var posAdapter = 0
     init {

         lyHome.visibility = View.GONE
        tvTitle.text = context.getString(R.string.home_title_banner)
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.home_item_banner, null, false)
         rvBanner = view.findViewById(R.id.rvBanner)
         indicator = view.findViewById(R.id.anyViewIndicator)
         mBannerAdapter = BannerAdapter(context, mListBanner)
        val layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(rvBanner)
        rvBanner.layoutManager = layoutManager
        rvBanner.adapter = mBannerAdapter
        rvBanner.addItemDecoration(MarginItemDecoration(14, MarginItemDecoration.TYPE_HORIZONTAL))


        mBannerAdapter.notifyDataSetChanged()
        indicator.setItemCount(5)
        indicator.setCurrentPosition(1)


        rvBanner.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)


                posAdapter++

                firstItemVisible = layoutManager.findFirstVisibleItemPosition()
                Log.e("firstVisible", firstItemVisible.toString())
                if (firstItemVisible != 1 && firstItemVisible % mListBanner.size == 1) {
                    recyclerView.layoutManager?.scrollToPosition(1)
                    posAdapter = 1
                }

                firstCompletelyItemVisible = layoutManager.findFirstCompletelyVisibleItemPosition()
                if (firstCompletelyItemVisible == 0) {
                    layoutManager.scrollToPositionWithOffset(mListBanner.size, 0)
                    posAdapter = mListBanner.size
                }

            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                when (newState) {
                    RecyclerView.SCROLL_STATE_IDLE -> {
                        val mposition = layoutManager.findLastVisibleItemPosition()

                        val pos = mposition % mListBanner.size
                        indicator.setCurrentPosition(pos)

                    }

                }

            }

        })

//        rvBanner.addItemDecoration(CirclePagerIndicatorDecoration(5))







        lyContainer.addView(view)
    }

    val handler = Handler()
    var pos = 1
    lateinit var runnable: Runnable
    private fun autoScroll(rvBanner: RecyclerView, firstItemVisible: Int) {

        runnable = object : Runnable {
            override fun run() {

                var max = firstItemVisible % mListBanner.size

                if (pos != mListBanner.size) {
                    pos++
                } else {
                    pos = 1
                }

                rvBanner.smoothScrollToPosition(pos)
                handler.postDelayed(this, 5000)
            }
        }
        handler.postDelayed(runnable, 5000)
    }

//        internal var ivIcon: ImageView


}