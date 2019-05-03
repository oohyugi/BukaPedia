package com.oohyugi.bukasempak.view.home

import android.app.Activity
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.oohyugi.bukasempak.R
import com.oohyugi.bukasempak.utils.MarginItemDecoration
import android.support.v4.view.animation.FastOutLinearInInterpolator
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import kotlin.math.abs
import android.os.Build
import android.os.CountDownTimer
import android.os.Handler
import android.support.annotation.RequiresApi
import android.support.annotation.Nullable
import android.support.v7.widget.*
import android.util.Log
import android.widget.*
import com.bumptech.glide.request.transition.Transition
import com.google.gson.Gson
import com.oohyugi.bukasempak.model.*
import com.oohyugi.bukasempak.utils.CirclePagerIndicatorDecoration
import com.oohyugi.bukasempak.view.home.menu.MenuListAdapter
import com.oohyugi.bukasempak.view.home.product.FlashDealListAdapterBL
import com.oohyugi.bukasempak.view.home.product.ProductListAdapterBL
import com.oohyugi.bukasempak.view.home.slider.BannerAdapter
import com.oohyugi.bukasempak.view.home.slider.DemoInfiniteAdapter
import me.relex.circleindicator.CircleIndicator
import me.relex.circleindicator.CircleIndicator2


/**
 * Created by oohyugi on 2019-04-24.
 * github: https://github.com/oohyugi
 */
class HomeListAdapterBL(private val context: Activity, private val list: List<BLHomeMdl>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val onItemClickListener: OnItemClickListener? = null
    //    private val mList:MutableList<BLHomeMdl> = mutableListOf()
    private var mFlashDealMdl: BaseFlashDealMdl? = null
    private var mListMenu: MutableList<MenuItemMdl> = mutableListOf()
    private var mListBanner: MutableList<BannerMdl> = mutableListOf()


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        var tvLoadMore: TextView = itemView.findViewById(R.id.tvMore)
        var lyContainer: LinearLayout = itemView.findViewById(R.id.ly_container)
        var lyHeader: LinearLayout = itemView.findViewById(R.id.ly_header)
        var lyHome: RelativeLayout = itemView.findViewById(R.id.ly_home)
//        internal var ivIcon: ImageView


    }

    class ViewHolderSlider(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        var tvLoadMore: TextView = itemView.findViewById(R.id.tvMore)
        var lyContainer: LinearLayout = itemView.findViewById(R.id.ly_container)
        var lyHeader: LinearLayout = itemView.findViewById(R.id.ly_header)
        var lyHome: RelativeLayout = itemView.findViewById(R.id.ly_home)
//        internal var ivIcon: ImageView


    }

    class ViewHolderSingleMenu(itemView: View) : RecyclerView.ViewHolder(itemView) {

//        var tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
//        var tvLoadMore: TextView = itemView.findViewById(R.id.tvMore)
//        var lyContainer: LinearLayout = itemView.findViewById(R.id.ly_container)
//        var lyHeader: LinearLayout = itemView.findViewById(R.id.ly_header)
//        var lyHome: RelativeLayout = itemView.findViewById(R.id.ly_home)
//        internal var ivIcon: ImageView


    }

    class ViewHolderDescLeft(itemView: View) : RecyclerView.ViewHolder(itemView) {

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


    }

    class ViewHolderFlashDeal(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitleLeft: TextView = itemView.findViewById(R.id.tvTitleLeft)
        var tvSubtitleLeft: TextView = itemView.findViewById(R.id.tvSubTitleLeft)
        var lyDescLeft: RelativeLayout = itemView.findViewById(R.id.ly_desc_left)
        var tvPriceLeft: TextView = itemView.findViewById(R.id.tvPriceLeft)
        var ivIconLeft: ImageView = itemView.findViewById(R.id.ivHomeLeft)
        var tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        var tvLoadMore: TextView = itemView.findViewById(R.id.tvMore)
        //        var tvLoadMoreLeft: TextView = itemView.findViewById(R.id.tvMoreLeft)
        var lyContainer: LinearLayout = itemView.findViewById(R.id.ly_container)
        var lyHeader: LinearLayout = itemView.findViewById(R.id.ly_header)
        var lyHome: RelativeLayout = itemView.findViewById(R.id.ly_home)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        var view: View?
        lateinit var viewHolder: RecyclerView.ViewHolder
        when (viewType) {
            TYPE_DEFAULT -> {
                view = inflater.inflate(R.layout.home_item, parent, false)
                viewHolder = ViewHolder(view)
            }
            TYPE_DESC_LEFT -> {
                view = inflater.inflate(R.layout.home_item_decs_left, parent, false)
                viewHolder = ViewHolderDescLeft(view)
            }
            TYPE_FLASHDEAL -> {
                view = inflater.inflate(R.layout.home_item_decs_left_flashdeal, parent, false)
                viewHolder = ViewHolderFlashDeal(view)
            }
            TYPE_MENU -> {
                view = inflater.inflate(R.layout.home_item, parent, false)
                viewHolder = ViewHolder(view)
            }
            TYPE_MENU_SINGLE -> {
                view = inflater.inflate(R.layout.home_item_menu_single, parent, false)
                viewHolder = ViewHolderSingleMenu(view)
            }
            TYPE_BANNER -> {
                view = inflater.inflate(R.layout.home_item, parent, false)
                viewHolder = ViewHolderSlider(view)
            }
            else -> {
                view = inflater.inflate(R.layout.home_item, parent, false)
                viewHolder = ViewHolder(view)
            }
        }

        return viewHolder


    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder.itemViewType) {
            TYPE_DEFAULT -> {
                val vh = holder as ViewHolder
                configureViewHolder(vh, position)
            }
            TYPE_DESC_LEFT -> {
                val vh = holder as ViewHolderDescLeft
                configureViewHoldeDescLeft(vh, position, TYPE_DESC_LEFT)
            }
            TYPE_FLASHDEAL -> {
                val vh = holder as ViewHolderFlashDeal
                configureViewHolderFlashDeal(vh, position)
            }
            TYPE_MENU -> {
                val vh = holder as ViewHolder
                configureViewHolderMenu(vh, position)
            }
            TYPE_MENU_SINGLE -> {
                val vh = holder as ViewHolderSingleMenu
                configureViewHolderDefaultMenu(vh, position)
            }
            TYPE_BANNER -> {
                val vh = holder as ViewHolderSlider
                configureViewHolderSlider(vh, position)
            }
        }


    }

    lateinit var mBannerAdapter: BannerAdapter
    private fun configureViewHolderSlider(holder: ViewHolderSlider, position: Int) {
        holder.tvTitle.text = "Spesial Untukmu"
        val view = LayoutInflater.from(context).inflate(R.layout.home_item_slider, null, false)

        val rvBanner: RecyclerView = view.findViewById(R.id.rvBanner)
        val indicator: CircleIndicator = view.findViewById(R.id.indicator)
        mBannerAdapter = BannerAdapter(context, mListBanner)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        val snapHelper =  PagerSnapHelper()
        snapHelper.attachToRecyclerView(rvBanner)
        indicator.attachToRecyclerView(rvBanner, snapHelper)

        mBannerAdapter.registerAdapterDataObserver(indicator.getAdapterDataObserver());
        rvBanner.layoutManager = layoutManager
        rvBanner.adapter = mBannerAdapter
        rvBanner.addItemDecoration(MarginItemDecoration(14, MarginItemDecoration.TYPE_HORIZONTAL))

        mBannerAdapter.notifyDataSetChanged()
        var firstItemVisible: Int =0
        var firstCompletelyItemVisible: Int = 0
        rvBanner.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                firstItemVisible = layoutManager.findFirstVisibleItemPosition()
                if (firstItemVisible != 1 && firstItemVisible % mListBanner.size == 1) {
                    recyclerView.layoutManager?.scrollToPosition(1)
                }

                firstCompletelyItemVisible = layoutManager.findFirstCompletelyVisibleItemPosition()
                if (firstCompletelyItemVisible == 0) {
                    layoutManager.scrollToPositionWithOffset(mListBanner.size, 0)
                }
            }

        })

        rvBanner.addItemDecoration(CirclePagerIndicatorDecoration(5))

//        autoScroll(rvBanner,firstItemVisible,firstCompletelyItemVisible)
        holder.lyContainer.addView(view)
//        initLayoutSlider(holder)


}

    fun autoScroll(rvBanner: RecyclerView, firstItemVisible: Int, firstCompletelyItemVisible: Int) {
        val handler = Handler()
        var pos = 0
        val runnable = object : Runnable {
            override fun run() {

                if (firstItemVisible != 1 && firstItemVisible % mListBanner.size == 1) {
                    pos =0
//                    rvBanner.smoothScrollToPosition(pos)
                }

                if (firstCompletelyItemVisible == 0) {
                    pos++

                }

                rvBanner.smoothScrollToPosition(pos)
                handler.postDelayed(this, 5000)
            }
        }
        handler.postDelayed(runnable, 5000)
    }

    lateinit var menuAdapter: MenuListAdapter
    private fun configureViewHolderMenu(holder: ViewHolder, position: Int) {
        holder.tvTitle.text = "E-Voucher, tiket,& investasi"
        holder.tvLoadMore.visibility = View.GONE
        val view = LayoutInflater.from(context).inflate(R.layout.home_item_menu, null, false)

        val rvMenu = view.findViewById<RecyclerView>(R.id.rvMenu)
        menuAdapter = MenuListAdapter(context, mListMenu)
        val layoutManager = GridLayoutManager(context, 4)
        rvMenu.layoutManager = layoutManager as RecyclerView.LayoutManager?
        rvMenu.adapter = menuAdapter
        rvMenu.addItemDecoration(MarginItemDecoration(14, MarginItemDecoration.TYPE_GRID_VERTICAL))
        menuAdapter.notifyDataSetChanged()
        holder.lyContainer.addView(view)

        Log.e("MenuItem", Gson().toJson(mListMenu))


    }

    private fun configureViewHolderFlashDeal(holder: ViewHolderFlashDeal, position: Int) {

        var title = ""
        var subtitleOne = ""
        var subTitleTwo = ""
        var mListProducts: MutableList<ItemsFlashDealMdl> = mutableListOf()

        val item = mFlashDealMdl?.flashDeal

        object : CountDownTimer(3000000, 1000) {
            override fun onTick(p0: Long) {
                var seconds = (p0 / 1000)
                val minutes = seconds / 60
                seconds %= 60

                subTitleTwo = "${minutes} : ${seconds}"
                holder.tvPriceLeft.text = subTitleTwo
                holder.tvTitle.text = subTitleTwo
            }

            override fun onFinish() {
                holder.tvPriceLeft.text = "Berakhir"
            }

        }.start()

//            }

//        val view = LayoutInflater.from(context).inflate(R.layout.home_item_flashdeal,null,false)
//
//        rvFlashdeal = view.findViewById<RecyclerView>(R.id.rvFlashdeal)
//
//        holder.lyContainer.addView(view)

//        layoutManager.reverseLayout = true


        if (item != null) {
            mListProducts.addAll(item.items!!)
            initLayoutProductFlashDeal(holder, mListProducts)
        }

        holder.tvTitleLeft.text = "FlashDeal"
        holder.tvSubtitleLeft.text = "Berakhir Dalam"
        holder.lyDescLeft.visibility = View.VISIBLE


    }


    private fun configureViewHoldeDescLeft(holder: ViewHolderDescLeft, position: Int, type: Int) {
        var title = ""
        var subtitleOne = ""
        var subTitleTwo = ""
        var imgHome = ""
        var typeProd = ""
        var bgHome = ""
        var mListProducts: MutableList<ProductsItemMdl> = mutableListOf()

        if (type == TYPE_DESC_LEFT) {
            val item = list[position - 4]

            typeProd = item.type
            title = item.title
            subtitleOne = item.subtitles!![0]
            if (item.subtitles.size > 1) {
                subTitleTwo = item.subtitles!![1]
            }
            imgHome = item.homePageImgUrl!!
            bgHome = item.image?.mobileUrl!!
            mListProducts.addAll(item.products!!)


        }

        holder.tvTitleLeft.text = title
        holder.tvTitle.text = title
        holder.tvSubtitleLeft.text = subtitleOne
        holder.tvPriceLeft.text = subTitleTwo

        Glide.with(context).load(imgHome).into(holder.ivIconLeft)
        if (typeProd.contains("Products Section", true)) {
            Glide.with(context)
                .load(bgHome)
                .into(object : CustomTarget<Drawable>() {
                    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
                    override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                        holder.lyHome.setBackground(resource)
                    }


                    override fun onLoadCleared(@Nullable placeholder: Drawable?) {

                    }
                })
            initLayoutProductDescLeft(holder, mListProducts)
        }
//        when(item.type){
//            "Product" -> {
//
//            }
//        }
    }


    private fun configureViewHolderDefaultMenu(holder: ViewHolderSingleMenu, position: Int) {
//        holder.tvTitle.visibility = View.GONE
//        holder.tvLoadMore.visibility = View.GONE
//        val view = LayoutInflater.from(context).inflate(R.layout.home_item_menu_single,null,false)
//        holder.lyContainer.addView(view)
    }

    private fun configureViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position - 4]
        holder.tvTitle.text = item.title

        if (item.type.contains("Products Section")) {
            initLayoutProduct(holder, item)
        }
//        when(item.type){
//            "default" -> {
//
//                initLayoutDefault(holder,item)
//            }
//
//            "slider" -> {
//                initLayoutSlider(holder,item)
//            }
//            "favorit" -> {
//                initLayoutFavorit(holder,item)
//            }
//            "product" -> {
//                initLayoutProduct(holder,item)
//            }
//        }

    }

    lateinit var productListAdapter: ProductListAdapterBL

    private fun initLayoutProduct(holder: ViewHolder, item: BLHomeMdl) {
        val view = LayoutInflater.from(context).inflate(R.layout.home_item_product, null, false)

        val rvProduct = view.findViewById<RecyclerView>(R.id.rvProduct)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        productListAdapter = ProductListAdapterBL(context, item.products!!, "default")
        rvProduct.layoutManager = layoutManager
        rvProduct.adapter = productListAdapter
        rvProduct.addItemDecoration(MarginItemDecoration(14, MarginItemDecoration.TYPE_HORIZONTAL))
        productListAdapter.notifyDataSetChanged()

        holder.lyContainer.addView(view)
    }


    var offset: Float = 0f
    var offsetPlus: Float = 0f

    private val interpolator = FastOutLinearInInterpolator()
    private fun initLayoutProductDescLeft(holder: ViewHolderDescLeft, item: List<ProductsItemMdl>?) {
//        holder.lyHome.setBackgroundColor(Color.parseColor(item.bgColor))
        val view = LayoutInflater.from(context).inflate(R.layout.home_item_product, null, false)

        val rvProduct = view.findViewById<RecyclerView>(R.id.rvProduct)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//        layoutManager.reverseLayout = true
        productListAdapter = ProductListAdapterBL(context, item!!, "default")
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
                val layoutManager = layoutManager as? LinearLayoutManager ?: return

                val scrollOffset = recyclerView.computeHorizontalScrollOffset()
                val offsetFactor =
                    (scrollOffset % holder.lyDescLeft.measuredWidth) / holder.lyDescLeft.measuredWidth.toFloat()

                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                offset = 1 - offsetFactor // Moves from -1 to 0
                offsetPlus = 1 + offsetFactor

                holder.lyDescLeft.alpha = offset
                val direction = if (offset < 0.1) -1f else 1f

                // ...and provide the absolute field value to the interpolator.
                val interpolatedValue = interpolator.getInterpolation(abs(offset))

                // Calculate the translation...
                val translationX = direction * 20 * interpolatedValue

                holder.lyDescLeft.translationX = translationX

                if (scrollOffset >= 0 && scrollOffset <= 253) {
                    holder.tvTitle.alpha = offsetFactor
                }

                holder.tvTitle.visibility = View.VISIBLE

                if (scrollOffset >= 253) {
                    holder.lyDescLeft.visibility = View.INVISIBLE
                } else {
                    holder.lyDescLeft.visibility = View.VISIBLE

                }
//                if (scrollOffset<=253){
//                    holder.tvTitle.visibility = View.INVISIBLE
//                }

//                Log.e("offset","${offsetFactor.toString()} firstVisibible ${firstVisibleItemPosition} scroll ${scrollOffset} translation ${translationX}" )
            }
        })
        holder.lyContainer.addView(view)

    }


    lateinit var flashListAdapter: FlashDealListAdapterBL

    private fun initLayoutProductFlashDeal(holder: ViewHolderFlashDeal, item: List<ItemsFlashDealMdl>?) {
//        holder.lyHome.setBackgroundColor(Color.parseColor(item.bgColor))
        val view = LayoutInflater.from(context).inflate(R.layout.home_item_flashdeal, null, false)

        val rvFlashdeal = view.findViewById<RecyclerView>(R.id.rvFlashdeal)
//        val layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
////        layoutManager.reverseLayout = true
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        flashListAdapter = FlashDealListAdapterBL(context, item!!)
        rvFlashdeal?.layoutManager = layoutManager
        rvFlashdeal?.adapter = flashListAdapter
        rvFlashdeal?.addItemDecoration(MarginItemDecoration(14, MarginItemDecoration.TYPE_HORIZONTAL))
        flashListAdapter.notifyDataSetChanged()
        rvFlashdeal?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = layoutManager as? LinearLayoutManager ?: return

                val scrollOffset = recyclerView.computeHorizontalScrollOffset()
                val offsetFactor = (scrollOffset % holder.lyDescLeft.width) / holder.lyDescLeft.width.toFloat()

                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                offset = 1 - offsetFactor // Moves from -1 to 0
                offsetPlus = 1 + offsetFactor

                holder.lyDescLeft.alpha = offset
                val direction = if (offset < 0.1) -1f else 1f

                // ...and provide the absolute field value to the interpolator.
                val interpolatedValue = interpolator.getInterpolation(abs(offset))

                // Calculate the translation...
                val translationX = direction * 20 * interpolatedValue

                holder.lyDescLeft.translationX = translationX

                if (scrollOffset >= 0 && scrollOffset <= 253) {
                    holder.tvTitle.alpha = offsetFactor
                    holder.tvLoadMore.alpha = offsetFactor
                }

                holder.tvTitle.visibility = View.VISIBLE
                holder.tvLoadMore.visibility = View.VISIBLE

                if (scrollOffset >= 253) {
                    holder.lyDescLeft.visibility = View.INVISIBLE
                } else {
                    holder.lyDescLeft.visibility = View.VISIBLE

                }
//                if (scrollOffset<=253){
//                    holder.tvTitle.visibility = View.INVISIBLE
//                }

//                Log.e("offset","${offsetFactor.toString()} firstVisibible ${firstVisibleItemPosition} scroll ${scrollOffset} translation ${translationX}" )
            }
        })
        holder.lyContainer.addView(view)

    }


    private fun initLayoutFavorit(holder: ViewHolder, item: BLHomeMdl) {
        holder.tvLoadMore.visibility = View.GONE
        holder.tvTitle.text = item.title
        val view = LayoutInflater.from(context).inflate(R.layout.home_item_menu_single, null, false)

        holder.lyContainer.addView(view)
    }


    lateinit var sliderPagerAdapter: DemoInfiniteAdapter

    private fun initLayoutSlider(holder: ViewHolderSlider) {

//        val manager = (context as AppCompatActivity).supportFragmentManager
//        sliderPagerAdapter = DemoInfiniteAdapter(context,mListBanner,true)
//        val view = LayoutInflater.from(context).inflate(R.layout.home_item_slider,null,false)
//        val vPSlider:LoopingViewPager = sliderView.findViewById(R.id.vpSlider)
//
//        vPSlider.adapter = sliderPagerAdapter
////        vPSlider.setCurrentItem(4,true)
//        vPSlider.currentItem = 2
////        vPSlider.setCurrentItem(0)
//        vPSlider.pageMargin = 14
//        vPSlider.clipToPadding  = false
//        vPSlider.setPadding(40, 0, 40, 0);
//        vPSlider.addOnPageChangeListener(object : CircularViewPagerHandler(vPSlider){
//
//        })


//


    }

    private fun initLayoutDefault(holder: ViewHolder, item: BLHomeMdl) {


//       holder.lyHeader.visibility = View.GONE
//        for ( data in item.items!!){
//            val defaultView = LayoutInflater.from(context).inflate(R.layout.home_item_icon_menu_single_line,null,false)
//            val tvTitleItem = defaultView.findViewById<TextView>(R.id.tvTitle)
//            tvTitleItem.text = data.title
////            val tvTitleItem = defaultView.findViewById<TextView>(R.id.tvTitle)
//            holder.lyContainer.addView(defaultView)
//
//
//        }

    }


    override fun getItemViewType(position: Int): Int {


        return if (position == 3) {
            TYPE_FLASHDEAL
        } else if (position == 2) {
            TYPE_MENU
        } else if (position == 1) {
            TYPE_BANNER
        } else if (position == 0) {
            TYPE_MENU_SINGLE
        } else {
            if (list[position - 4].type.contains("Popular Products Section", true)) {
                TYPE_DESC_LEFT

            } else TYPE_DEFAULT
//            TYPE_DESC_LEFT
        }

    }


    override fun getItemCount(): Int {

        return list.size + 4
    }

    private fun getRealPosition(position: Int): Int {
        return position - position / 1

    }

    fun addItem(mBaseFlashDealMdl: BaseFlashDealMdl?) {
//        var newValue:BLHomeMdl? =null
//        newValue?.type = "flashdeal"
//        newValue?.flashDeal = mBaseFlashDealMdl
//        mList.add(newValue!!)

//        notifyDataSetChanged()
    }

    fun addItemFlashDeal(mBaseFlashDealMdl: BaseFlashDealMdl?) {
        mFlashDealMdl = mBaseFlashDealMdl
//        flashListAdapter.notifyDataSetChanged()
//      notifyDataSetChanged()


    }

    fun addItemMenu(list: MutableList<MenuItemMdl>) {
        var i = 0
        var sortedList = list.sortedWith(compareBy { it.ordering })
        for (item in sortedList) {
            i++
            if (i < 9) {
                mListMenu.add(item)

            }

            menuAdapter.notifyDataSetChanged()
        }


    }

    fun addItemBanner(list: MutableList<BannerMdl>) {
        mListBanner.addAll(list)
        mBannerAdapter.notifyDataSetChanged()
//        sliderPagerAdapter.notifyDataSetChanged()
        Log.e("banner", Gson().toJson(mListBanner))
    }


    interface OnItemClickListener {

    }

    companion object {

        private val TAG = HomeListAdapterBL::class.java.simpleName
        private val TYPE_DEFAULT = 4
        private val TYPE_DESC_LEFT = 5
        private val TYPE_FLASHDEAL = 3
        private val TYPE_MENU = 2
        private val TYPE_BANNER = 1
        private val TYPE_MENU_SINGLE = 0
    }
}