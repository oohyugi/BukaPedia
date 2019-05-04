package com.oohyugi.bukasempak.view.home.product

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.annotation.RequiresApi
import androidx.interpolator.view.animation.FastOutLinearInInterpolator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.oohyugi.bukasempak.R
import com.oohyugi.bukasempak.model.BLHomeMdl
import com.oohyugi.bukasempak.model.ProductsItemMdl
import com.oohyugi.bukasempak.utils.MarginItemDecoration
import kotlin.math.abs

class ViewHolderProductHorizontal(itemView: View, val context: Context) : RecyclerView.ViewHolder(itemView) {
    lateinit var productListAdapter: ProductListAdapterBL
    var mListProducts: MutableList<ProductsItemMdl> = mutableListOf()
    var data: BLHomeMdl? = null
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
    var lyHome: RelativeLayout = itemView.findViewById(R.id.ly_home)


    fun setItem(items: BLHomeMdl) {
        lyHome.visibility = View.VISIBLE
        data = items
        var title: String? = ""
        var subtitleOne: String? = ""
        var subTitleTwo: String? = ""
        var imgHome: String? = ""
        var typeProd: String? = ""
        var bgHome: String? = ""
        initLayoutProducts()
        if (data != null) {

            typeProd = data?.type
            title = data?.title
            subtitleOne = data?.subtitles!![0]
            if (data?.subtitles!!.size > 1) {
                subTitleTwo = data?.subtitles!![1]
            }
            imgHome = data?.homePageImgUrl!!
            bgHome = data?.image?.mobileUrl!!
            mListProducts.clear()
            mListProducts.addAll(data?.products!!)
            productListAdapter.notifyDataSetChanged()
        }


//                        mListProducts.addAll(item?.products!!)


        tvTitleLeft.text = title
        tvTitle.text = title
        tvSubtitleLeft.text = subtitleOne
        tvPriceLeft.text = subTitleTwo

        Glide.with(context).load(imgHome).into(ivIconLeft)
        if (typeProd!!.contains("Products Section", true)) {
            Glide.with(context)
                .load(bgHome)
                .into(object : CustomTarget<Drawable>() {
                    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
                    override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                        lyHome.setBackground(resource)
                    }


                    override fun onLoadCleared(@Nullable placeholder: Drawable?) {

                    }
                })

        }

    }


    private fun initLayoutProducts() {
        val view = LayoutInflater.from(context).inflate(R.layout.home_item_product_horizontal, null, false)

        val rvProduct = view.findViewById<RecyclerView>(R.id.rvProduct)
        val layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
//        layoutManager.reverseLayout = true
        productListAdapter = ProductListAdapterBL(context, mListProducts, "default")
        rvProduct.layoutManager = layoutManager
        rvProduct.adapter = productListAdapter
        rvProduct.addItemDecoration(MarginItemDecoration(14, MarginItemDecoration.TYPE_HORIZONTAL))
        productListAdapter.notifyDataSetChanged()
        rvProduct.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val scrollOffset = recyclerView.computeHorizontalScrollOffset()
                val offsetFactor =
                    (scrollOffset % lyDescLeft.measuredWidth) / lyDescLeft.measuredWidth.toFloat()
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
                }

                tvTitle.visibility = View.VISIBLE

                if (scrollOffset >= 253) {
                    lyDescLeft.visibility = View.INVISIBLE
                } else {
                    lyDescLeft.visibility = View.VISIBLE

                }
//                if (scrollOffset<=253){
//                    tvTitle.visibility = View.INVISIBLE
//                }

//                Log.e("offset","${offsetFactor.toString()} firstVisibible ${firstVisibleItemPosition} scroll ${scrollOffset} translation ${translationX}" )
            }
        })
        lyContainer.addView(view)
    }

}