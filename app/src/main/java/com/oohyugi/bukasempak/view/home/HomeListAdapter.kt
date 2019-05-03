package com.oohyugi.bukasempak.view.home

import android.app.Activity
import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.oohyugi.bukasempak.R
import com.oohyugi.bukasempak.model.HomeMdl
import androidx.recyclerview.widget.LinearLayoutManager
import android.util.Log
import android.widget.ImageView
import com.oohyugi.bukasempak.utils.MarginItemDecoration
import com.oohyugi.bukasempak.view.home.product.ProductListAdapter
import androidx.interpolator.view.animation.FastOutLinearInInterpolator
import kotlin.math.abs


/**
 * Created by oohyugi on 2019-04-24.
 * github: https://github.com/oohyugi
 */
class HomeListAdapter(private val context: Activity, private val list: List<HomeMdl>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val onItemClickListener: OnItemClickListener? = null



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

         var tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
         var tvLoadMore: TextView = itemView.findViewById(R.id.tvMore)
         var lyContainer: LinearLayout = itemView.findViewById(R.id.ly_container)
         var lyHeader: LinearLayout = itemView.findViewById(R.id.ly_header)
         var lyHome: RelativeLayout = itemView.findViewById(R.id.ly_home)
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        var view: View?
        lateinit var viewHolder: RecyclerView.ViewHolder
        when (viewType){
            TYPE_DEFAULT -> {
                view = inflater.inflate(R.layout.home_item, parent, false)
                viewHolder = ViewHolder(view)
            }
            TYPE_DESC_LEFT -> {
                view = inflater.inflate(R.layout.home_item_decs_left, parent, false)
                viewHolder = ViewHolderDescLeft(view)
            }
            else->{
                view = inflater.inflate(R.layout.home_item, parent, false)
                viewHolder = ViewHolder(view)
            }
        }

        return viewHolder





    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when(holder.itemViewType){
            TYPE_DEFAULT -> {
                val vh = holder as ViewHolder
                configureViewHolder(vh,position)
            }
            TYPE_DESC_LEFT -> {
                val vh = holder as ViewHolderDescLeft
                configureViewHoldeDescLeft(vh,position)
            }
        }



    }

    private fun configureViewHoldeDescLeft(holder: ViewHolderDescLeft, position: Int) {
        val item = list[position]
        holder.tvTitleLeft.text = item.title
        holder.tvTitle.text = item.title
        holder.tvSubtitleLeft.text = item.subTitle
        holder.tvPriceLeft.text = item.headline
        when(item.type){
            "product" -> {
                initLayoutProductDescLeft(holder,item)
            }
        }
    }



    private fun configureViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.tvTitle.text = item.title
        when(item.type){
            "default" -> {

                initLayoutDefault(holder,item)
            }

            "slider" -> {
                initLayoutSlider(holder,item)
            }
            "favorit" -> {
                initLayoutFavorit(holder,item)
            }
            "product" -> {
                initLayoutProduct(holder,item)
            }
        }

    }

    lateinit var productListAdapter: ProductListAdapter

    private fun initLayoutProduct(holder: ViewHolder, item: HomeMdl) {


        holder.lyHome.setBackgroundColor(Color.parseColor(item.bgColor))
        val view = LayoutInflater.from(context).inflate(R.layout.home_item_product_horizontal,null,false)

        val rvProduct = view.findViewById<RecyclerView>(R.id.rvProduct)
        val layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        productListAdapter = ProductListAdapter(context, item.items!!, item.typeItems)
        rvProduct.layoutManager = layoutManager
        rvProduct.adapter = productListAdapter
        rvProduct.addItemDecoration(MarginItemDecoration(14,MarginItemDecoration.TYPE_HORIZONTAL))
        productListAdapter.notifyDataSetChanged()

        holder.lyContainer.addView(view)
    }



    var offset :Float = 0f
    var offsetPlus :Float = 0f

    private val interpolator = FastOutLinearInInterpolator()
    private fun initLayoutProductDescLeft(holder: ViewHolderDescLeft, item: HomeMdl) {

        holder.lyHome.setBackgroundColor(Color.parseColor(item.bgColor))
        val view = LayoutInflater.from(context).inflate(R.layout.home_item_product_horizontal,null,false)

        val rvProduct = view.findViewById<RecyclerView>(R.id.rvProduct)
        val layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
//        layoutManager.reverseLayout = true
        productListAdapter = ProductListAdapter(context,item.items!!,item.typeItems)
        rvProduct.layoutManager = layoutManager
        rvProduct.adapter = productListAdapter
        rvProduct.addItemDecoration(MarginItemDecoration(14,MarginItemDecoration.TYPE_HORIZONTAL))
        productListAdapter.notifyDataSetChanged()
        rvProduct.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = layoutManager as? LinearLayoutManager ?: return

                val scrollOffset = recyclerView.computeHorizontalScrollOffset()
                val offsetFactor = (scrollOffset % holder.lyDescLeft.measuredWidth) / holder.lyDescLeft.measuredWidth.toFloat()

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

                if (scrollOffset>=0 && scrollOffset<=253 ){
                    holder.tvTitle.alpha = offsetFactor
                }

                holder.tvTitle.visibility = View.VISIBLE

                if (scrollOffset>= 253 ){
                    holder.lyDescLeft.visibility = View.INVISIBLE
                }
                else{
                    holder.lyDescLeft.visibility = View.VISIBLE

                }
//                if (scrollOffset<=253){
//                    holder.tvTitle.visibility = View.INVISIBLE
//                }

                Log.e("offset","${offsetFactor.toString()} firstVisibible ${firstVisibleItemPosition} scroll ${scrollOffset} translation ${translationX}" )
            }
        })
        holder.lyContainer.addView(view)

    }

    private fun initLayoutFavorit(holder: ViewHolder, item: HomeMdl) {
        holder.tvLoadMore.visibility = View.GONE
        holder.tvTitle.text = item.title
        val view = LayoutInflater.from(context).inflate(R.layout.home_item_menu_single,null,false)

        holder.lyContainer.addView(view)
    }

    private fun initLayoutSlider(holder: ViewHolder, item: HomeMdl) {

//        val manager = (context as AppCompatActivity).supportFragmentManager
////        sliderPagerAdapter = SliderPagerAdapter(manager, item.items!!)
//        val sliderView = LayoutInflater.from(context).inflate(R.layout.home_item_slider,null,false)
//        val vPSlider = sliderView.findViewById<ViewPager>(R.id.vpSlider)
//        vPSlider.adapter = sliderPagerAdapter
//
////        vPSlider.setCurrentItem(0)
//        vPSlider.pageMargin = 14
//        vPSlider.clipToPadding  = false
//        vPSlider.setPadding(40, 0, 40, 0);
//        vPSlider.addOnPageChangeListener(object : CircularViewPagerHandler(vPSlider){
//
//        })
//        holder.lyContainer.addView(sliderView)




    }

    private fun initLayoutDefault(holder: ViewHolder, item: HomeMdl) {


       holder.lyHeader.visibility = View.GONE
        for ( data in item.items!!){
            val defaultView = LayoutInflater.from(context).inflate(R.layout.menu_item,null,false)
            val tvTitleItem = defaultView.findViewById<TextView>(R.id.tvTitle)
            tvTitleItem.text = data.title
//            val tvTitleItem = defaultView.findViewById<TextView>(R.id.tvTitle)
            holder.lyContainer.addView(defaultView)


        }

    }


    override fun getItemViewType(position: Int): Int {
        return if (list[position].type == "product") {
            TYPE_DESC_LEFT
        } else {
            TYPE_DEFAULT
        }

    }


    override fun getItemCount(): Int {
        return list.size
    }


     interface OnItemClickListener{

    }

    companion object {

        private val TAG = HomeListAdapter::class.java.simpleName
        private val TYPE_DEFAULT = 0
        private val TYPE_DESC_LEFT = 1
    }
}