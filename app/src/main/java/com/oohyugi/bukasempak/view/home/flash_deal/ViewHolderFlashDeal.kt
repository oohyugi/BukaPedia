package com.oohyugi.bukasempak.view.home.flash_deal

import android.content.Context
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.fragment.app.FragmentActivity
import androidx.interpolator.view.animation.FastOutLinearInInterpolator
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.oohyugi.bukasempak.R
import com.oohyugi.bukasempak.model.BaseFlashDealMdl
import com.oohyugi.bukasempak.model.ItemsFlashDealMdl
import com.oohyugi.bukasempak.utils.MarginItemDecoration
import com.oohyugi.bukasempak.view.home.HomeViewModel
import kotlin.math.abs




class ViewHolderFlashDeal(itemView: View,val context: Context) : RecyclerView.ViewHolder(itemView) {

        var mListProducts: MutableList<ItemsFlashDealMdl> = mutableListOf()
        lateinit var viewModel:HomeViewModel
         var mFlashDealMdl:BaseFlashDealMdl? =null
//        fun setItem(item: BaseFlashDealMdl?) {
//                mFlashDealMdl = item
//
//
//        }
        lateinit var flashListAdapter: FlashDealListAdapterBL
        var offset: Float = 0f
        var offsetPlus: Float = 0f

        private val interpolator = FastOutLinearInInterpolator()
        var tvTitleLeft: TextView = itemView.findViewById(R.id.tvTitleLeft)
        var tvSubtitleLeft: TextView = itemView.findViewById(R.id.tvSubTitleLeft)
        var lyDescLeft: RelativeLayout = itemView.findViewById(R.id.ly_desc_left)
        var tvPriceLeft: TextView = itemView.findViewById(R.id.tvPriceLeft)
        var ivIconLeft: ImageView = itemView.findViewById(R.id.ivHomeLeft)
        var tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        var tvLoadMore: TextView = itemView.findViewById(R.id.tvMore)
        var lyContainer: LinearLayout = itemView.findViewById(R.id.ly_container)
        var lyHeader: LinearLayout = itemView.findViewById(R.id.ly_header)
        var lyHome: RelativeLayout = itemView.findViewById(R.id.ly_home)
        var progress: ProgressBar = itemView.findViewById(R.id.progress)

        init {

                var subTitleTwo: String
                lyHome.visibility = View.GONE
                viewModel = ViewModelProviders.of(context as FragmentActivity).get(HomeViewModel::class.java)
                progress.visibility = View.VISIBLE
                viewModel.flashDealMdl
                        .observe(context,
                                Observer<BaseFlashDealMdl> {
                                        mFlashDealMdl = it
                                        if (mFlashDealMdl != null) {
                                                progress.visibility = View.GONE
                                                lyHome.visibility = View.VISIBLE
                                                mListProducts.clear()
                                                mListProducts.addAll(mFlashDealMdl!!.flashDeal.items!!)

                                        }

                                        flashListAdapter.notifyDataSetChanged()
                                        Log.e("callViewModelFlash",Gson().toJson(it))

                                })








                initLayoutProductFlashDeal()
                object : CountDownTimer(3000000, 1000) {
                        override fun onTick(p0: Long) {
                                var seconds = (p0 / 1000)
                                val minutes = seconds / 60
                                seconds %= 60

                                subTitleTwo = "${minutes} : ${seconds}"
                                tvPriceLeft.text = subTitleTwo
                                tvTitle.text = subTitleTwo
                        }

                        override fun onFinish() {
                               tvPriceLeft.text = context.getString(R.string.berakhir)
                        }

                }.start()




                tvTitleLeft.text = context.getString(R.string.flashdeal)
               tvSubtitleLeft.text = context.getString(R.string.flasdeal_sub_one)
                lyDescLeft.visibility = View.VISIBLE
        }



        private fun initLayoutProductFlashDeal() {
                val view = LayoutInflater.from(context).inflate(R.layout.home_item_flashdeal, null, false)

                val rvFlashdeal = view.findViewById<RecyclerView>(R.id.rvFlashdeal)
                val mLayoutManager = LinearLayoutManager(
                        context,
                        LinearLayoutManager.HORIZONTAL,
                        false
                )
                flashListAdapter =
                        FlashDealListAdapterBL(context, mListProducts)
                rvFlashdeal?.layoutManager = mLayoutManager
                rvFlashdeal?.adapter = flashListAdapter
                rvFlashdeal?.addItemDecoration(MarginItemDecoration(14, MarginItemDecoration.TYPE_HORIZONTAL))
                flashListAdapter.notifyDataSetChanged()
                rvFlashdeal?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                                super.onScrollStateChanged(recyclerView, newState)

                        }

                        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                                super.onScrolled(recyclerView, dx, dy)
                                val scrollOffset = recyclerView.computeHorizontalScrollOffset()
                                val offsetFactor = (scrollOffset % lyDescLeft.width) / lyDescLeft.width.toFloat()
                                offset = 1 - offsetFactor // Moves from -1 to 0
                                offsetPlus = 1 + offsetFactor

                                lyDescLeft.alpha = offset
                                val direction = if (offset < 0.1) -1f else 1f

                                // ...and provide the absolute field value to the interpolator.
                                val interpolatedValue = interpolator.getInterpolation(abs(offset))

                                // Calculate the translation...
                                val translationX = direction * 20 * interpolatedValue

                                lyDescLeft.translationX = translationX

                                if (scrollOffset >= 0 && scrollOffset <= 253) {
                                        tvTitle.alpha = offsetFactor
                                        tvLoadMore.alpha = offsetFactor
                                }

                               tvTitle.visibility = View.VISIBLE
                                tvLoadMore.visibility = View.VISIBLE

                                if (scrollOffset >= 253) {
                                        lyDescLeft.visibility = View.INVISIBLE
                                } else {
                                        lyDescLeft.visibility = View.VISIBLE

                                }
                                }
                })

                lyContainer.addView(view)

        }

}